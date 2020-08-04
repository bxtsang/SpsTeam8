package com.google.sps.services.implementations;

import javax.inject.Inject;

import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.UserRoom;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.util.FirebaseUtil;
import com.google.sps.util.TimestampUtil;

public class JoinRoomRoomServiceImpl implements JoinRoomService {
    private AuthenticationHandler authenticationHandler;
    private FirebaseUtil firebaseUtil;

    @Inject
    public JoinRoomRoomServiceImpl(AuthenticationHandler authenticationHandler, FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();

        firebaseUtil.addUserRoom(userEmail, postJoinRequest.getRoomId());

        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .setTimestamp(TimestampUtil.getTimestamp())
                .build();
    }
}
