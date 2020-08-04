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
import com.google.sps.data.UserRoom;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

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
        
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public void addUserRoom(String userEmail, String roomId) {
        FirebaseDatabase.getInstance()
            .getReference("UserRoom")
            .push()
            .setValue(new UserRoom(userEmail, roomId), (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            });
    }

    public boolean hasUserJoinedRoom(String userEmail, String roomId) throws IOException {
        final boolean[] isUserInRoom = { false };
        CountDownLatch done = new CountDownLatch(1);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserRoom");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userEmailRoom").getValue().equals(userEmail + "_" + roomId)) {
                        isUserInRoom[0] = true;
                    }
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("error");
            }
        });

        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        return isUserInRoom[0];
    }
}