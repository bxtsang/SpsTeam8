package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.UserRoom;
import com.google.sps.dataManagers.UserRoomManager;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;

public class JoinRoomServiceImpl implements JoinRoomService {
    private AuthenticationHandler authenticationHandler;
    private UserRoomManager userRoomManager;

    @Inject
    public JoinRoomServiceImpl(AuthenticationHandler authenticationHandler,
                               UserRoomManager userRoomManager) {
        this.authenticationHandler = authenticationHandler;
        this.userRoomManager = userRoomManager;
    }

    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) throws ServletException {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();
        UserRoom userRoom = userRoomManager.addUserRoom(userEmail, postJoinRequest.getRoomId());
        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .build();
    }
}
