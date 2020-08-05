package com.google.sps.services.implementations;

import javax.inject.Inject;

import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.UserRoom;
import com.google.sps.dataManagers.UserRoomManager;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.util.FirebaseUtil;
import com.google.sps.util.TimestampUtil;

public class JoinRoomRoomServiceImpl implements JoinRoomService {
    private AuthenticationHandler authenticationHandler;
    private UserRoomManager userRoomManager;

    @Inject
    public JoinRoomRoomServiceImpl(AuthenticationHandler authenticationHandler,
                                   UserRoomManager userRoomManager) {
        this.authenticationHandler = authenticationHandler;
        this.userRoomManager = userRoomManager;
    }

    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();
        userRoomManager.addUserRoom(userEmail, postJoinRequest.getRoomId());
        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .build();
    }
}
