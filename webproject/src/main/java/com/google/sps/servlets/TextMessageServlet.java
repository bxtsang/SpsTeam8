package com.google.sps.servlets;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.appengine.api.users.User;
import com.google.sps.data.Message;
import com.google.sps.util.TimestampUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.database.FirebaseDatabase;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextMessageServlet extends HttpServlet {
    private static String username;

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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the URL of the image that the user uploaded to Blobstore.
        String message = request.getParameter("textmessage");

        String referrer = request.getHeader("referer");
        String[] array = referrer.split("\\?");
        String roomID = array[1];
        FirebaseDatabase.getInstance()
            .getReference("messages")
            .child(roomID)
            .push()
            .setValueAsync(new Message(username, message, "text"));
      
        response.sendRedirect(referrer);
    }
}
