package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@WebServlet("/myRooms")
public class MyRoomsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = AuthenticationHandler.getCurrentUser();
        String userEmail = user.getEmail();

        String url = "https://summer20-sps-47.firebaseio.com/UserRoom.json?orderBy=%22userEmail%22&equalTo=%22" + userEmail + "%22";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonResponse = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder firebaseResponse = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                firebaseResponse.append(line);
            }

            jsonResponse = firebaseResponse.toString();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonResponse);
    }
}
