package com.potpourri.service.account;

public final class BusySleep {

    public static void sleep(long millis)
    {
        long elapsed;
        final long startTime = System.currentTimeMillis();
        do {
            elapsed = System.currentTimeMillis() - startTime;
        } while (elapsed < millis);
    }
}
