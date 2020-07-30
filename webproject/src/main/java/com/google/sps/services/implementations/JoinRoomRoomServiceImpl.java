package com.google.sps.services.implementations;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.appengine.api.users.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.data.UserRoom;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.authentication.AuthenticationHandlerSupplier;
import com.google.sps.util.TimestampUtil;

public class JoinRoomRoomServiceImpl implements JoinRoomService {
        @Override
    public FirebaseOptions getFirebaseOptions() throws IOException {
        // Fetch the service account key JSON file contents
        FileInputStream serviceAccount = new FileInputStream("./key.json");

        // Initialize the app with a service account, granting admin privileges
        return new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://summer20-sps-47.firebaseio.com")
                .build();
    }

    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) {
        String userEmail = AuthenticationHandlerSupplier.getAuthenticationHandler().getCurrentUser().getEmail();

        FirebaseDatabase.getInstance()
                .getReference("UserRoom")
                .push()
                .setValueAsync(new UserRoom(userEmail, postJoinRequest.getRoomId()));

        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .setTimestamp(TimestampUtil.getTimestamp())
                .build();
    }
}
