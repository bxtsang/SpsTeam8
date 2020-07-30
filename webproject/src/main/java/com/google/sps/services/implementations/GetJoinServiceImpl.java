package com.google.sps.services.implementations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.appengine.api.users.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.sps.proto.GetJoinProto.GetJoinRequest;
import com.google.sps.services.interfaces.GetJoinService;
import com.google.sps.util.AuthHandlerUtil;

public class GetJoinServiceImpl implements GetJoinService {
    @Override
    public String execute(GetJoinRequest getJoinRequest) throws IOException {
        String roomId = getJoinRequest.getRoomId();
        User user = getCurrentUser();
        String userEmail = user.getEmail();

        StringBuilder url = new StringBuilder("https://summer20-sps-47.firebaseio.com/UserRoom.json?orderBy=%22userEmailRoom%22&equalTo=%22");
        url.append(userEmail);
        url.append("_");
        url.append(roomId);
        url.append("%22");

        //Use Firebase class once merged
        HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonResponse = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder firebaseResponse = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                firebaseResponse.append(line);
            }

            jsonResponse = firebaseResponse.toString();
            return jsonResponse;
        }
    }

    public User getCurrentUser() {
        return AuthHandlerUtil.getAuthenticationHandler().getCurrentUser();
    }
}
