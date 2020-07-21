package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Order;
import com.google.sps.data.Room;
import com.google.sps.firebase.Firebase;

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
        User user = new AuthenticationHandler().getCurrentUser();

        Order newOrder = Order.newBuilder()
                .setUserEmail(user.getEmail())
                .setProduct(request.getParameter("product"))
                .setQuantity(Integer.parseInt(request.getParameter("quantity")))
                .setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")))
                .setRoomId(request.getParameter("roomId"))
                .build();
        String newOrderJson = gson.toJson(newOrder);

        String urlString = "https://summer20-sps-47.firebaseio.com/orders.json";
        Firebase.sendRequest(urlString, "POST", newOrderJson);

        String roomId = request.getParameter("roomId");
        Room room = Room.getRoomById(roomId);
        room.addOrdersValue(newOrder.getOrderPrice());

        response.setStatus(200);
    }
}
