package com.google.sps.firebase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Firebase {
    public static String sendRequest(String urlString, String method, String requestBody) throws IOException {
        System.out.println("I'M IN");
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String jsonResponse = "";

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder firebaseResponse = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                firebaseResponse.append(responseLine.trim());
            }

            jsonResponse = firebaseResponse.toString();
        }
        System.out.println(jsonResponse);
        return jsonResponse;
    }

    public static String sendGetRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonResponse = "";

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder firebaseResponse = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                firebaseResponse.append(responseLine.trim());
            }

            jsonResponse = firebaseResponse.toString();
        }

        return jsonResponse;
    }
}
