package org.example;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.container.NodeTags;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int radius = 2500;

        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(35.36231, -119.04136);
        // Define the Overpass API query
        String query = "[out:json];node(around:" + radius + ", " + cartesianCoordinate + ")[\"building\"=\"yes\"];out;";
        String secondQuery = "[out:json];way(around:" + radius + ", " + cartesianCoordinate + ")[\"building\"=\"yes\"];node(w);out;";

        // Check if the request was successful
        Gson gson = new Gson();
        OverpassResponse overpassResponse = gson.fromJson(QueryProcessor.processQuery(query), OverpassResponse.class);
        OverpassResponse overpassResponse1 = gson.fromJson(QueryProcessor.processQuery(secondQuery), OverpassResponse.class);
        List<Element> elements = overpassResponse.getElements();
        elements.addAll(overpassResponse1.getElements());
        NewWorkBook newWorkBook = new NewWorkBook("workbook.xlsx");
        Sheet main = newWorkBook.createSheet("main", new String[]{"Address", "Zipcode", "Ward", "Park"});
        int count = 1;
        Row row = main.createRow(count++);
//        List<String> addresses = new ArrayList<>();
//        for (Element element : elements) {
//            try {
//                ReverseGeocodingResponse reverseGeocodingResponse = QueryProcessor.reverseGeocode(element.getLat(), element.getLon());
//                String string = reverseGeocodingResponse.toString();
//                if(!addresses.contains(string)) {
//                    addresses.add(string);
//                    System.out.println(string);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
        Workbook wb = new XSSFWorkbook();

        try {
            OutputStream out = new FileOutputStream("workbook.xlsx");
            wb.write(out);

            Sheet newSheet = wb.createSheet("New Sheet");
            wb.write(out);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}