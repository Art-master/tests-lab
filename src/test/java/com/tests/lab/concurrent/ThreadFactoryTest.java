package com.tests.lab.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {

    public static class TestThreadFactory implements ThreadFactory {

        private int threadId;
        private final String name;

        public TestThreadFactory(String name) {
            threadId = 1;
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name + "-Thread_" + threadId);
            System.out.println("created new thread with id : " + threadId + " and name : " + t.getName());
            threadId++;
            return t;
        }
    }


    @Test
    void Test() {
        TestThreadFactory factory = new TestThreadFactory("TestThreadFactory");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = factory.newThread(() -> System.out.println("Execute thread " + finalI));
            t.start();
        }
    }
}
