package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Category;
import com.google.sps.data.Room;
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

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
    private static Gson gson = new Gson();

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new AuthenticationHandler().getCurrentUser();
        String userEmail = user.getEmail();

        Room newRoom = Room.newBuilder()
                .setTitle(request.getParameter("title"))
                .setLink(request.getParameter("link"))
                .setDescription(request.getParameter("description"))
                .setDeliveryLocation(Integer.parseInt(request.getParameter("deliveryLocation")))
                .setPhoneNumber(Integer.parseInt(request.getParameter("phoneNumber")))
                .setCategory(Category.valueOf(request.getParameter("category").toUpperCase()))
                .setMinPrice(Double.parseDouble(request.getParameter("minPrice")))
                .setDeliveryFee(Double.parseDouble(request.getParameter("deliveryFee")))
                .setCreator(userEmail)
                .build();

        newRoom.addUser(userEmail);

        newRoom.save();
        response.setStatus(200);
    }
}
