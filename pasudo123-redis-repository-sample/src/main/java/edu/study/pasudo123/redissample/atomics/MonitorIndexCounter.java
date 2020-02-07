package edu.study.pasudo123.redissample.atomics;

import java.util.concurrent.atomic.AtomicLong;

public final class MonitorIndexCounter {

    private static AtomicLong counter = new AtomicLong(0);

    static Long getIndex() {
        return counter.getAndIncrement();
    }
}
