package com.tests.lab.threads.data;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.TimeUnit;

public class DelayQueueUsingTimedSemaphore {
    private final TimedSemaphore semaphore;

    public DelayQueueUsingTimedSemaphore(long period, int slotLimit) {
        semaphore = new TimedSemaphore(period, TimeUnit.SECONDS, slotLimit);
    }

    public boolean tryAdd() {
        return semaphore.tryAcquire();
    }

    public int availableSlots() {
        return semaphore.getAvailablePermits();
    }
}
