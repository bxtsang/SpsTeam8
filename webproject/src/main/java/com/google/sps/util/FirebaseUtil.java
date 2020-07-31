package com.google.sps.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.sps.data.UserRoom;

public class FirebaseUtil {
    @Inject
    public FirebaseUtil() {
        try {
            // Fetch the service account key JSON file contents
            FileInputStream serviceAccount = new FileInputStream("./key.json");

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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getFirebaseResponse(String url) throws IOException {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserRoom");
        System.out.println(ref.toString());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println("Document: " + document.toString());
            }
            
            public void onCancelled(DatabaseError error) {
            }
        });

        return "Meap";

    //    HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
    //    con.setRequestMethod("GET");
    //    con.setRequestProperty("Accept", "application/json");
    //    con.setDoOutput(true);

    //    String jsonResponse = "";
    //    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
    //        StringBuilder firebaseResponse = new StringBuilder();
    //        String line;

    //        while ((line = in.readLine()) != null) {
    //            firebaseResponse.append(line);
    //        }

    //        jsonResponse = firebaseResponse.toString();
    //        return jsonResponse;
    //    }
    }
}
