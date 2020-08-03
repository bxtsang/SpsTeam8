package com.google.sps.servlets;

import com.google.appengine.api.users.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.Message;
import com.google.sps.util.HtmlParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which renders the website's listings page.
 */
public class FetchMessagesServlet extends HttpServlet {

    private static String username;
    private static Gson gson = new Gson();

    @Override
    public void init() {
        try {
            // Fetch the service account key JSON file contents
            FileInputStream serviceAccount = new FileInputStream("./key.json");

            // Initialize the app with a service account, granting admin privileges
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://summer20-sps-47.firebaseio.com")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        AuthenticationHandler handler = new AuthenticationHandler();
        User curr = handler.getCurrentUser();
        username = curr.getNickname();
    }


    /**
     * Called by the server to allow this servlet to handle a GET request from the listings page.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CountDownLatch done = new CountDownLatch(1);

        String roomId = request.getParameter("roomId");
        System.out.println("Roomid: " + roomId);

        for (String name: Collections.list(request.getParameterNames())) {
            System.out.println("Param: " + name);
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("messages/" + roomId);
        System.out.println("Ref: " + ref.toString());
        
        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> messages = new ArrayList<>();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    messages.add(data.getValue());
                }

                response.setContentType("application/json;charset=UTF-8");
                try {
                    response.getWriter().println(gson.toJson(messages));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
