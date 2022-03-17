package com.tests.lab.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new Task());
        service.submit(new Task());
        service.submit(new Task());

        executeCallables(service);

        try {
            //await all submitted threads and stop
            service.shutdown();

            //blocking current thread
            boolean isComplete = service.awaitTermination(6, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End main method");
    }

    private static void executeCallables(ExecutorService service) {
        Callable<Boolean> task = () -> true;
        Callable<Boolean> task2 = () -> false;
        Callable<Boolean> task3 = () -> true;
        List<Callable<Boolean>> tasks = Arrays.asList(task, task2, task3);

        try {
            List<Future<Boolean>> futures = service.invokeAll(tasks);
            futures.forEach((a) -> {
                try {
                    System.out.println("get callable value = " + a.get() + " in " + Thread.currentThread().getName());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task was execute in " + Thread.currentThread().getName());
        }
    }
}
