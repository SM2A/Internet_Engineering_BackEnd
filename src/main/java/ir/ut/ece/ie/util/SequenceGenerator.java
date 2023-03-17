package ir.ut.ece.ie.util;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceGenerator {
    private static AtomicLong value = new AtomicLong(1);

    public static Long getNext() {
        return value.getAndIncrement();
    }
}
