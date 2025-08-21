package com.trading.common.util;

import java.time.Instant;

public class DateUtils {
    public static String currentISOTime() {
        return Instant.now().toString();
    }
}

