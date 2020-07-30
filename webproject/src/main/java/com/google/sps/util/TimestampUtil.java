package com.google.sps.util;

import java.sql.Timestamp;

public class TimestampUtil {
    public static long getTimestamp() {
        return (new Timestamp(System.currentTimeMillis())).getTime();
    }
}
