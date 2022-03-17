package com.tests.lab.threads;

import com.tests.lab.threads.data.CounterUsingMutex;
import com.tests.lab.threads.data.DelayQueueUsingTimedSemaphore;
import com.tests.lab.threads.data.LoginQueueUsingSemaphore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Semaphore tests")
public class SemaphoreTest {

    @Test
    @DisplayName("Обычный Semaphore")
    public void givenLoginQueue_whenReachLimit_thenBlocked() throws InterruptedException {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);

        IntStream.range(0, slots).forEach(user -> executorService.execute(loginQueue::tryLogin));

        executorService.shutdown();
        boolean result = executorService.awaitTermination(1, TimeUnit.SECONDS);

        assertEquals(0, loginQueue.availableSlots());
        loginQueue.logout();

        assertTrue(result);
        assertTrue(loginQueue.availableSlots() > 0);
        assertTrue(loginQueue.tryLogin());
    }

    @Test
    public void givenDelayQueue_whenReachLimit_thenBlocked() throws InterruptedException {
        int slots = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);

        IntStream.range(0, slots).forEach(user -> executorService.execute(delayQueue::tryAdd));
        executorService.shutdown();

        boolean result = executorService.awaitTermination(1, TimeUnit.SECONDS);

        assertTrue(result);
        assertEquals(0, delayQueue.availableSlots());

        assertFalse(delayQueue.tryAdd());
        Thread.sleep(1000);
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertTrue(delayQueue.availableSlots() > 0);
    }


    @Test
    public void whenMutexAndMultipleThreads_thenBlocked() throws InterruptedException {
        int count = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        CounterUsingMutex counter = new CounterUsingMutex();

        IntStream.range(0, count).forEach(user -> executorService.execute(() -> {
            try {
                counter.increase();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        assertTrue(counter.hasQueuedThreads());

        Thread.sleep(5000);
        assertFalse(counter.hasQueuedThreads());
        assertEquals(count, counter.getCount());

    }


}
