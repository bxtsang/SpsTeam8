package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Order;
import com.google.sps.data.Room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static Gson gson = new Gson();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = AuthenticationHandler.getCurrentUser();

        Order newOrder = Order.newBuilder()
                .setUserEmail(user.getEmail())
                .setProduct(request.getParameter("product"))
                .setQuantity(Integer.parseInt(request.getParameter("quantity")))
                .setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")))
                .build();

        String roomId = request.getParameter("roomId");
        Room room = getRoom(roomId);
        room.addOrder(newOrder);
        String urlString = "https://summer20-sps-47.firebaseio.com/rooms/" + roomId + ".json";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(false);

        String roomJson = gson.toJson(room);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = roomJson.getBytes("utf-8");
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

        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().print(jsonResponse);
    }

    private Room getRoom(String roomId) throws IOException{
        String urlString = "https://summer20-sps-47.firebaseio.com/rooms/" + roomId + ".json";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

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

        return gson.fromJson(jsonResponse, Room.class);
    }
}
