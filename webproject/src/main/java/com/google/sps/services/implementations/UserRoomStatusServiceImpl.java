package com.google.sps.services.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.sps.proto.UserRoomStatusProto;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.authentication.AuthenticationHandlerSupplier;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class UserRoomStatusServiceImpl implements UserRoomStatusService {
    private FirebaseUtil firebaseUtil;

    @Inject
    public UserRoomStatusServiceImpl(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    @Override
    public String execute(UserRoomStatusProto.UserRoomStatusRequest getJoinRequest) throws IOException {
        String roomId = getJoinRequest.getRoomId();
        String userEmail = AuthenticationHandlerSupplier.getAuthenticationHandler().getCurrentUser().getEmail();

        StringBuilder url = new StringBuilder("https://summer20-sps-47.firebaseio.com/UserRoom.json?orderBy=%22userEmailRoom%22&equalTo=%22");
        url.append(userEmail);
        url.append("_");
        url.append(roomId);
        url.append("%22");

        return firebaseUtil.getFirebaseResponse(url.toString());
    }
}
