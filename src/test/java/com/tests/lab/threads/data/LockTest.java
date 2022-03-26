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
                Thread.sleep(1000);
                System.out.println("Inside object thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void performTryLock() throws InterruptedException {
            boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);

            if (isLockAcquired) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Object not locked: " + Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Test
    void ReentrantLockTest() {
        SharedObject object = new SharedObject();

        Thread thread = new Thread(object::perform);
        Thread thread2 = new Thread(() -> {
            try {
                object.performTryLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread2.start();
    }
}
