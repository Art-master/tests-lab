package com.tests.lab.threads.core;

import org.junit.jupiter.api.Test;

public class ThreadExceptionTests {

    private static class MyThread extends Thread {

        public boolean isThrowException = false;

        @Override
        public void run() {
            if (isThrowException)
                throw new RuntimeException("Error");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyrUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + ": " + e.getMessage());
        }
    }

    @Test
    void threadExceptionTest() {
        MyThread thread = new MyThread();
        thread.isThrowException = true;
        thread.setUncaughtExceptionHandler(new MyrUncaughtExceptionHandler());
        thread.start();
    }
}
