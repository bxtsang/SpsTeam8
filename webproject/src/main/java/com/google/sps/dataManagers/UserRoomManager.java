package com.google.sps.dataManagers;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.sps.data.UserRoom;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class UserRoomManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public UserRoomManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public void addUserRoom(String userEmail, String roomId) {
        UserRoom userRoom = UserRoom.newBuilder().setUserEmail(userEmail).setRoomId(roomId).setUserEmailRoom().build();

        firebaseUtil.getUserRoomReference().push()
            .setValue(userRoom, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            });
    }
}
