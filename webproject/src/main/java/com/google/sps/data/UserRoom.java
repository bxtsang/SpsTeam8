package com.google.sps.data;

import com.google.firebase.database.FirebaseDatabase;

public class UserRoom {
    private String userEmail;
    private String roomId;
    private String userEmailRoom;

    public UserRoom(String userEmail, String roomId) {
        this.userEmail = userEmail;
        this.roomId = roomId;
        this.userEmailRoom = userEmail + "_" + roomId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getUserEmailRoom() {
        return userEmailRoom;
    }

    public void save() {
        FirebaseDatabase.getInstance().getReference("UserRoom").push().setValueAsync(this);
    }
}
