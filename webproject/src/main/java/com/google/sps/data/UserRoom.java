package com.google.sps.data;

public class UserRoom {
    private String userEmail;
    private String roomId;

    public UserRoom(String userEmail, String roomId) {
        this.userEmail = userEmail;
        this.roomId = roomId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getRoomId() {
        return roomId;
    }
}
