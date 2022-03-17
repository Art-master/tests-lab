package com.tests.lab.threads;

import java.util.concurrent.Executor;

public class ExecutorTest {

    public static void main(String[] args) {

        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };

        executor.execute(() -> {
            System.out.println("Command 1 executed in " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            System.out.println("Command 2 executed in " + Thread.currentThread().getName());
        });

    }
}
