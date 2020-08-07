package com.google.sps.data;

public class Message {
    private String user;
    private String time;
    private String message;
    private String type;

    public Message(String user, String message, String type, String time) {
        this.user = user;
        this.time = time;
        this.message = message;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
