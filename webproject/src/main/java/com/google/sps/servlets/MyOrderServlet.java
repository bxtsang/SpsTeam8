package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Category;
import com.google.sps.data.Room;
import com.google.sps.firebase.Firebase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

@WebServlet("/myOrder")
public class MyOrderServlet extends HttpServlet {
    //private static Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new AuthenticationHandler().getCurrentUser();
        String userEmail = user.getEmail();
        String roomId = request.getParameter("roomId");

        String firebaseResponse = Firebase.sendGetRequest("https://summer20-sps-47.firebaseio.com/orders.json?orderBy=%22userEmailRoomId%22&equalTo=%22" + userEmail + "_" + roomId + "%22");
        response.setStatus(200);
        response.getWriter().print(firebaseResponse);
    }     
    
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String orderId = reader.readLine();
        orderId = orderId.split("=")[1];
        String orderData = Firebase.sendGetRequest("https://summer20-sps-47.firebaseio.com/orders/" + orderId + ".json");

        if (!Objects.equals(orderData, "null") && isValidOrder(orderData)) {
            String firebaseResponse = Firebase.sendRequest("https://summer20-sps-47.firebaseio.com/orders/" + orderId + ".json", "DELETE", orderId);
            response.setStatus(200);
            response.getWriter().print(firebaseResponse);
        }
    }

    private Boolean isValidOrder(String orderData) {
        JsonObject orderJson = new JsonParser().parse(orderData).getAsJsonObject();
        String key = "";

        if (orderJson.entrySet().size() != 7) {
            return false;
        }

        for (java.util.Map.Entry<String, JsonElement> mp: orderJson.entrySet()) {
            key = mp.getKey();
            if (!key.equals("orderPrice") && !key.equals("product") && !key.equals("quantity") 
                && !key.equals("roomId") && !key.equals("unitPrice") && !key.equals("userEmail")
                && !key.equals("userEmailRoomId")) {
                    return false;
            }
        }

        return true;
    }
}
