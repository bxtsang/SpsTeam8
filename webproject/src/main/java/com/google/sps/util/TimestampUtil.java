package com.google.sps.util;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimestampUtil {
    public static long getTimestamp() {
        return Instant.now().getEpochSecond();
    }
}
