package com.google.sps.data;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Message {
    private String user;
    private long timestamp;
    private String message;
    private String type;

    public Message(String user, String imageUrl, String type) {
        long currTime = System.currentTimeMillis();
        this.user = user;
        this.timestamp = currTime;
        this.message = imageUrl;
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
