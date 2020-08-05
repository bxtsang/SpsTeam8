package com.google.sps.dataManagers;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.sps.data.UserRoomProto.UserRoom;
import com.google.sps.util.FirebaseUtil;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserRoomManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public UserRoomManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public UserRoom addUserRoom(String userEmail, String roomId) throws ServletException {
        if (!isRoomIdValid(roomId)) {
            throw new ServletException("Invalid roomId");
        }

        String userEmailRoom = userEmail + "_" + roomId;
        UserRoom userRoom =
                UserRoom.newBuilder().setUserEmail(userEmail).setRoomId(roomId).setUserEmailRoom(userEmailRoom).build();
        
        Map<String, String> userRoomMapping = new HashMap<>();
        userRoomMapping.put("userEmail", userEmail);
        userRoomMapping.put("roomId", roomId);
        userRoomMapping.put("userEmailRoom", userEmailRoom);

        firebaseUtil.getUserRoomReference().push()
                .setValue(userRoomMapping, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        try {
                            throw new ServletException("Invalid roomId");
                        } catch (ServletException e) {
                            e.printStackTrace();
                        }
                    }
                });

        return userRoom;
    }

    public Optional<DataSnapshot> getUserRoom(String userEmail, String roomId) throws ServletException {
        if (!isRoomIdValid(roomId)) {
            throw new ServletException("Invalid roomId");
        }

        String userEmailRoom = userEmail + "_" + roomId;
        Query query = firebaseUtil.getUserRoomReference().orderByChild("userEmailRoom");
        return firebaseUtil.getQuerySnapshot(query, userEmailRoom);
    }

    private boolean isRoomIdValid(String roomId) throws ServletException {
        Query query = firebaseUtil.getRoomsReference().orderByKey();
        Optional<DataSnapshot> room = firebaseUtil.getQuerySnapshot(query, roomId);
        return room.isPresent();
    }
}
