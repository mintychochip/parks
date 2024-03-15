package org.example;

import com.google.gson.Gson;
import org.example.container.NodeTags;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        String secondQuery = "[out:json];way(around:" + radius + ", " + cartesianCoordinate + ")[\"building\"=\"house\"];out;";

        // Check if the request was successful
        Gson gson = new Gson();
        OverpassResponse overpassResponse = gson.fromJson(QueryProcessor.processQuery(query), OverpassResponse.class);
        OverpassResponse overpassResponse1 = gson.fromJson(QueryProcessor.processQuery(secondQuery), OverpassResponse.class);
        System.out.println(QueryProcessor.processQuery(query));
        List<Element> elements = overpassResponse.getElements();
        elements.addAll(overpassResponse1.getElements());
        System.out.println(QueryProcessor.processQuery(secondQuery));
        for (Element element : elements) {
            System.out.println(element.getLat() +" " + element.getLon());
        }

    }
}