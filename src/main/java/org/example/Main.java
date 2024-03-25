package org.example;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.container.NodeTags;
import org.example.container.Park;
import org.example.container.Ward;
import org.example.enums.BuildingTypes;
import org.example.enums.NodeType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Main {

    public Map<Park, List<String>> parksAndAddresses = new HashMap<>();
    public static final String QUERY = "[out:json];&node-type(around:&radius,&coord)[\"building\"=\"&type\"];&opt;out;";

    public static final List<NodeType> checkedNodeTypes = Arrays.stream(new NodeType[]{NodeType.WAY, NodeType.NODE}).toList();
    public static final List<BuildingTypes> checkedBuildingTypes = Arrays.stream(new BuildingTypes[]{BuildingTypes.YES, BuildingTypes.HOUSE}).toList();

    private static List<Park> evaluatedParks;

    public static void main(String[] args) {

        int radius = 250;

        int count = 1;
        System.setProperty("log4j.configurationFile", "./path_to_the_log4j2_config_file/log4j2.xml");
        Set<String> addresses = new HashSet<>();
        NewWorkBook newWorkBook = new NewWorkBook("Z:\\workbook.xlsx");
        Sheet mainSheet = newWorkBook.createSheet("main sheet", new String[]{"Address", "Lat", "Lon", "Ward"});
        mainSheet.createRow(count++).createCell(0).setCellValue("//this sheet is auto-generated");
        evaluatedParks = registerParks(radius);
        for (Park x : evaluatedParks) {
            for (OverpassResponse overpassResponse : QueryProcessor.processParkQueries(x)) {
                if (overpassResponse != null) {
                    for (Element y : overpassResponse.getElements()) {
                        try {
                            String s = QueryProcessor.reverseGeocode(y.getLat(), y.getLon()).toString() + " " + x.getWard();
                            if (addresses.add(s)) {
                                Row row = mainSheet.createRow(count++);
                                row.createCell(0).setCellValue(s);
                                row.createCell(1).setCellValue(y.getLat());
                                row.createCell(2).setCellValue(y.getLon());
                                row.createCell(3).setCellValue(x.getWard().toString());
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        newWorkBook.write();
    }

    private static List<Park> registerParks(int radius) { //gotta move this to a config this is ghetto
        List<Park> evaluatedParks = new ArrayList<>();
        evaluatedParks.add(new Park.ParkBuilder()
                .setCoordinate(new CartesianCoordinate(35.36231, -119.04136))
                .setName("Saunders")
                .setWard(Ward.WARD_2).setRadius(radius).build());
        evaluatedParks.add(new Park.ParkBuilder()
                .setCoordinate(new CartesianCoordinate(35.37446, -119.03531))
                .setName("Jastro")
                .setWard(Ward.WARD_2).build());
        return evaluatedParks;
    }
}