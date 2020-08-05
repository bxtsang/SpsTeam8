package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.UserRoomProto.UserRoom;
import com.google.sps.dataManagers.UserRoomManager;
import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusResponse;
import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusRequest;
import com.google.sps.services.interfaces.UserRoomStatusService;

@Singleton
public class UserRoomStatusServiceImpl implements UserRoomStatusService {
    private AuthenticationHandler authenticationHandler;
    private UserRoomManager userRoomManager;

    @Inject
    public UserRoomStatusServiceImpl(AuthenticationHandler authenticationHandler, UserRoomManager userRoomManager) {
        this.userRoomManager = userRoomManager;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public UserRoomStatusResponse execute(UserRoomStatusRequest getJoinRequest) throws ServletException {
        String roomId = getJoinRequest.getRoomId();
        String userEmail = authenticationHandler.getCurrentUser().getEmail();

        UserRoom userRoom = userRoomManager.getUserRoom(userEmail, roomId);
        boolean isJoined = false;

        if (userRoom != null) {
            isJoined = true;
        }

        return UserRoomStatusResponse.newBuilder()
                .setIsJoined(isJoined)
                .setRoomId(roomId)
                .build();
    }
}
