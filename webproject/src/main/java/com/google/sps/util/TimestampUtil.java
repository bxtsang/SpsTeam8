package com.google.sps.util;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimestampUtil {
    public static long getTimestamp() {
        return Instant.now().getEpochSecond();
    }

    public static String getHhMmTimestamp() {
        long miliSeconds = getTimestamp();
        int hrs = (int) TimeUnit.MILLISECONDS.toHours(miliSeconds) % 24;
        int min = (int) TimeUnit.MILLISECONDS.toMinutes(miliSeconds) % 60;
        return String.format("%02d:%02d", hrs, min);
    }
}
