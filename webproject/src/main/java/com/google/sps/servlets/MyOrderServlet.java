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

@WebServlet("/myOrder")
public class MyOrderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new AuthenticationHandler().getCurrentUser();
        String userEmail = user.getEmail();
        String roomId = request.getParameter("roomId");

        String firebaseResponse = Firebase.sendGetRequest("https://summer20-sps-47.firebaseio.com/orders.json?orderBy=%22userEmailRoomId%22&equalTo=%22" + userEmail + "_" + roomId + "%22");
        response.setStatus(200);
        response.getWriter().print(firebaseResponse);
    }
}
