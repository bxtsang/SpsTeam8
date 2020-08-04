package com.google.sps.util;

import java.time.Instant;

public class TimestampUtil {
    public static long getTimestamp() {
        return Instant.now().getEpochSecond();
    }
}
