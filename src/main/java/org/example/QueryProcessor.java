package org.example;

import com.google.gson.Gson;
import org.example.container.Park;
import org.example.enums.BuildingTypes;
import org.example.enums.NodeType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryProcessor {

    private static String apiUrl = "http://overpass-api.de/api/interpreter?data=";

    private static String reverseGeoCode = "https://nominatim.openstreetmap.org/reverse?lat=35.3555814&lon=-119.0237178&format=json&extratags=1";
    public static OverpassResponse processQuery(String query) {
        try {
            String encode = URLEncoder.encode(query, "UTF-8");
            URL url = new URL(apiUrl + encode);
            URLConnection urlConnection = url.openConnection();
            if(urlConnection instanceof HttpURLConnection httpURLConnection) {
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLn;
                    StringBuilder response = new StringBuilder();
                    while((inputLn = in.readLine()) != null) {
                        response.append(inputLn);
                    }
                    return new Gson().fromJson(response.toString(), OverpassResponse.class);
                } else {
                    System.out.println(responseCode);
                    return null;
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static ReverseGeocodingResponse reverseGeocode(double lat, double lon) throws IOException {
        String url = "https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon + "&format=json";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        ReverseGeocodingResponse reverseGeocodingResponse = null;

        try {
            if (conn.getResponseCode() == 200) {
                StringBuilder result = new StringBuilder();
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    while (scanner.hasNextLine()) {
                        result.append(scanner.nextLine());
                    }
                }
                Gson gson = new Gson();
                reverseGeocodingResponse = gson.fromJson(result.toString(), ReverseGeocodingResponse.class);
            } else {
                System.out.println("Error: " + conn.getResponseMessage());
            }
        } finally {
            conn.disconnect(); // Ensure connection is closed
        }

        return reverseGeocodingResponse;
    }

    public static List<OverpassResponse> processParkQueries(Park park) {
        String query = Main.QUERY.replaceAll("&radius", park.getRadius() + "").replaceAll("&coord", park.getCartesian().toString());
        List<OverpassResponse> responses = new ArrayList<>();
        for (NodeType nodeType : Main.checkedNodeTypes) {
            for (BuildingTypes checkedBuildingType : Main.checkedBuildingTypes) {
                String newQuery = query.replaceAll("&node-type", nodeType.getKey()).replaceAll("&type", checkedBuildingType.getKey());
                newQuery = nodeType == NodeType.WAY ? newQuery.replaceAll(";&opt",";node(w)") : newQuery.replaceAll(";&opt","");
                responses.add(processQuery(newQuery));
            }
        }
        return responses;
    }


}
