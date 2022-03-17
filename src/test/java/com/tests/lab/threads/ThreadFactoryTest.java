package com.tests.lab.threads;

import com.tests.lab.threads.data.TestThreadFactory;
import org.junit.jupiter.api.Test;

public class ThreadFactoryTest {

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
