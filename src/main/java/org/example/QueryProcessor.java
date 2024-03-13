package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class QueryProcessor {

    private static String apiUrl = "http://overpass-api.de/api/interpreter?data=";
    public static String processQuery(String query) {
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
                    return response.toString();
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
}
