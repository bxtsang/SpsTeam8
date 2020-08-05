package com.google.sps.util;

import java.io.FileInputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Singleton
public class FirebaseUtil {
    @Inject
    public FirebaseUtil() throws Exception {
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
    }

    public DatabaseReference getUserRoomReference() {
        return FirebaseDatabase.getInstance()
            .getReference("UserRoom");
    }

}
