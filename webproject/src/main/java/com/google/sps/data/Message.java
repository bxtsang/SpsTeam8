package com.google.sps.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    public String type;
    public String user;
    public String message;
    public String time;

    public Message(String user, String url) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String str = sdf.format(new Date());
        this.type = "image";
        this.user = user;
        this.message = url;
        this.time = str;
    }
}
