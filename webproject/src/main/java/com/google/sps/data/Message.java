package com.google.sps.data;

import com.google.sps.util.TimestampUtil;

public class Message {
    private String user;
    private long timestamp;
    private String message;
    private String type;

    public Message(String user, String message, String type, String time) {
        long currTime = TimestampUtil.getTimestamp();
        this.user = user;
        this.time = time;
        this.message = message;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
