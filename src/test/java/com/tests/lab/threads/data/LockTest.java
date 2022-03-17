package com.tests.lab.threads.data;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static class SharedObject {
        ReentrantLock lock = new ReentrantLock();
        int counter = 0;

        public void perform() {
            lock.lock();
            try {
                System.out.println("Inside object thread: " + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }

        public void performTryLock() throws InterruptedException {
            boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);

            if (isLockAcquired) {
                try {
                    System.out.println("Object not locked: " + Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Test
    void ReentrantLockTest() {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "executed"));
        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "executed"));
    }
}
