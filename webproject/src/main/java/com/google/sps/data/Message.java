package com.google.sps.data;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Message {
    private String user;
    private String time;
    private String message;
    private String type;

    public Message(String user, String imageUrl) {
        String currTime = new SimpleDateFormat("HH:mm").format(new Date());
        this.user = "me";
        this.time = currTime;
        this.message = imageUrl;
        this.type = "image";
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
