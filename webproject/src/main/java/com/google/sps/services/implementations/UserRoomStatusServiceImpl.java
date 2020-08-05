package com.google.sps.services.implementations;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.dataManagers.UserRoomManager;
import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusRequest;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.util.FirebaseUtil;

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
    public boolean execute(UserRoomStatusRequest getJoinRequest) throws InterruptedException {
        String roomId = getJoinRequest.getRoomId();
        String userEmail = authenticationHandler.getCurrentUser().getEmail();
        return userRoomManager.hasUserJoinedRoom(userEmail, roomId);
    }
}
