package com.tests.lab.threads;

import java.util.concurrent.*;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> System.out.println("task 1"), 4, TimeUnit.SECONDS);
        service.schedule(() -> System.out.println("task 2"), 1, TimeUnit.SECONDS);

        //await all submitted threads and stop
        service.shutdown();

        System.out.println("End main method");
    }
}
