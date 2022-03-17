package com.tests.lab.threads.data;

import java.util.concurrent.ThreadFactory;

public class TestThreadFactory implements ThreadFactory {

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
