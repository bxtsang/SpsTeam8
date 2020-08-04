package com.google.sps.services.implementations;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.UserRoomStatusProto;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class UserRoomStatusServiceImpl implements UserRoomStatusService {
    private FirebaseUtil firebaseUtil;
    private AuthenticationHandler authenticationHandler;

    @Inject
    public UserRoomStatusServiceImpl(FirebaseUtil firebaseUtil, AuthenticationHandler authenticationHandler) {
        this.firebaseUtil = firebaseUtil;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public boolean execute(UserRoomStatusProto.UserRoomStatusRequest getJoinRequest) throws IOException {
        String roomId = getJoinRequest.getRoomId();
        String userEmail = authenticationHandler.getCurrentUser().getEmail();

        String url = "UserRoom";

        return firebaseUtil.hasUserJoinedRoom(url, userEmail, roomId);
    }
}
