package com.google.sps.data;

public class UserRoom {
    private String userEmail;
    private String roomId;
    private String userEmailRoom;

    public UserRoom(Builder builder) {
        this.userEmail = builder.userEmail;
        this.roomId = builder.roomId;
        this.userEmailRoom = builder.userEmailRoom;
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String userEmail;
        private String roomId;
        private String userEmailRoom;

        public Builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder setRoomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setUserEmailRoom() {
            this.userEmailRoom = this.userEmail + "_" + this.roomId;
            return this;
        }

        public UserRoom build() {
            return new UserRoom(this);
        }
    }

}
