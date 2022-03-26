package com.tests.lab.threads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Четкая последовательность выполнения потоков")
public class ThreadsSequencesTest {

    @Test
    @DisplayName("Через join")
    void joinTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1");
        });

        Thread thread2 = new Thread(() -> System.out.println("Thread 2"));
        Thread thread3 = new Thread(() -> System.out.println("Thread 3"));

        thread1.start();
        thread1.join(); // Ждем пока первый поток выполнится

        thread2.start();
        thread2.join(); // Ждем пока второй поток выполнится

        thread3.start();
    }
}
