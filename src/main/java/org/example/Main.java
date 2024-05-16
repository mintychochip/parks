package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.build.Query;
import org.example.container.Park;
import org.example.enums.BuildingTypes;
import org.example.enums.Relation;

import java.io.*;
import java.util.*;

public class Main {

    public Map<Park, List<String>> parksAndAddresses = new HashMap<>();
    public static final String QUERY = "[out:json];&node-type(around:&radius,&coord)[\"building\"=\"&type\"];&opt;out;";

    public static final List<Relation> CHECKED_RELATIONS = Arrays.stream(new Relation[]{Relation.WAY, Relation.NODE}).toList();
    public static final List<BuildingTypes> checkedBuildingTypes = Arrays.stream(new BuildingTypes[]{BuildingTypes.YES, BuildingTypes.HOUSE}).toList();

    public static void main(String[] args) {

        int radius = 1500;

        int count = 1;
        List<Park> parks = new ArrayList<>();
        Query build = Query.builder().setTags(tags -> {
            tags.addTag("asd", "asd");
        }).setRelation(Relation.WAY).build();
        Relation relation = build.getRelation();
        
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(35.37458, -119.03515);
        System.setProperty("log4j.configurationFile", "./path_to_the_log4j2_config_file/log4j2.xml");
        Set<String> addresses = new HashSet<>();
        String query = new QueryImpl.QueryBuilder().setRadius(radius, cartesianCoordinate).setConvertWayToNode(false).setNodeType(Relation.WAY).addTag("leisure", "park").addTag("owner", "City of Bakersfield").build().getQuery();
        if (query != null) {
            OverpassResponse overpassResponse = QueryProcessor.processQuery(query);
            for (Element element : overpassResponse.getElements()) {
                ReverseGeocodingResponse reverseGeocodingResponse1 = QueryProcessor.reverseGeocodeByTypeAndId(element.getNodeType(), element.getId());
                if (reverseGeocodingResponse1 != null) {
                    System.out.println(reverseGeocodingResponse1.getAddress().getPark());
                    parks.add(new Park(reverseGeocodingResponse1, 125, reverseGeocodingResponse1.getAddress().getPark()));
                }
            }
        }
        for (Park park : parks) {
            NewWorkBook newWorkBook = new NewWorkBook("Z:\\park-address\\" + park.getName() + ".xlsx");
            Sheet mainSheet = newWorkBook.createSheet(park.getName(), new String[]{"Address", "Lat", "Lon", "Park"});
            mainSheet.createRow(count++).createCell(0).setCellValue("//this sheet is auto-generated");
            List<OverpassResponse> overpassResponses = QueryProcessor.processParkQueries(park);
            for (OverpassResponse overpassRespons : overpassResponses) {
                for (Element element : overpassRespons.getElements()) {
                    ReverseGeocodingResponse reverseGeocodingResponse = null;
                    try {
                        reverseGeocodingResponse = QueryProcessor.reverseGeocodeByCartesian(element.getLat(), element.getLon());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (reverseGeocodingResponse != null) {
                        if (reverseGeocodingResponse.getAddresstype().equals("place")) {
                            Row row = mainSheet.createRow(count++);
                            row.createCell(0).setCellValue(reverseGeocodingResponse.toString());
                            row.createCell(1).setCellValue(reverseGeocodingResponse.getLat());
                            row.createCell(2).setCellValue(reverseGeocodingResponse.getLon());
                            row.createCell(3).setCellValue(park.getName());
                        }
                    }
                }
            }
            newWorkBook.write();
            count = 1;
        }
    }
}