package com.google.sps.services.implementations;

import javax.inject.Inject;

import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.UserRoom;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.util.TimestampUtil;

public class JoinRoomRoomServiceImpl implements JoinRoomService {
    private AuthenticationHandler authenticationHandler;

    @Inject
    public JoinRoomRoomServiceImpl(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();

        FirebaseDatabase.getInstance()
                .getReference("UserRoom")
                .push()
                .setValue(new UserRoom(userEmail, postJoinRequest.getRoomId()), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        System.out.println("Data could not be saved " + databaseError.getMessage());
                    } else {
                        System.out.println("Data saved successfully.");
                    }
                });

        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .setTimestamp(TimestampUtil.getTimestamp())
                .build();
    }
}
