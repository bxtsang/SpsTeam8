package com.google.sps.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;

@Singleton
public class FirebaseUtil {
    @Inject
    public FirebaseUtil() throws Exception {
        // Fetch the service account key JSON file contents
        FileInputStream serviceAccount = new FileInputStream("/home/abahety/SpsTeam8/webproject/src/main/webapp/key.json");

        // Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://summer20-sps-47.firebaseio.com")
                .build();

        System.out.println("*******************************");
        System.out.println("CALLINNNGGGGGGGG");
        System.out.println("*******************************");
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public String getFirebaseResponse(String url) throws IOException {
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserRoom");
        // ref.addListenerForSingleValueEvent(new ValueEventListener() {
        //     @Override
        //     public void onDataChange(DataSnapshot dataSnapshot) {
        //         Object document = dataSnapshot.getValue();
        //         System.out.println("Document: " + document.toString());
        //     }
            
        //     @Override
        //     public void onCancelled(DatabaseError error) {
        //         System.out.println("error");
        //     }
        // });
        // System.out.println(ref.toString());

        // return "Meap";

        //Use Firebase class once merged
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
        System.out.println("Json response: " + jsonResponse);
        return jsonResponse;
    }
}