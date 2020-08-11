package com.google.sps.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class TimestampUtil {
    public static long getTimestamp() {
        return Instant.now().getEpochSecond();
    }

    public static String getFormattedTime() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }
}
