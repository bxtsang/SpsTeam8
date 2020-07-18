package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Message;
import com.google.sps.data.UserRoom;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
    @Override
    public void init() {
        try {
            // Fetch the service account key JSON file contents
            FileInputStream serviceAccount = new FileInputStream("/home/mtang/cloudshell_open/SpsTeam8-0/webproject/src/main/java/com/google/sps/servlets/key.json");

            // Initialize the app with a service account, granting admin privileges
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://summer20-sps-47.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthenticationHandler auth = new AuthenticationHandler();
        if (!auth.isUserLoggedIn()) {
            response.setStatus(400);
            return;
        }

        User user = AuthenticationHandler.getCurrentUser();
        String userEmail = user.getEmail();
        String roomId = request.getParameter("roomId");
        FirebaseDatabase.getInstance().getReference("messages").push().setValueAsync(new UserRoom(userEmail, roomId));

        response.setStatus(200);
        response.getWriter().println(roomId);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roomId = request.getParameter("roomId");
        User user = AuthenticationHandler.getCurrentUser();
        String userEmail = user.getEmail();

        String url = "https://summer20-sps-47.firebaseio.com/UserRoom.json?orderBy=%22userEmailRoom%22&equalsTo=%22" + userEmail + "_" + roomId + "%22";

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

        response.setContentType("text/plain");

        if (jsonResponse.substring(0, 2) == "{}") {
            response.getWriter().print("Join");
        } else {
            response.getWriter().print("Chat");
        }
    }
}
