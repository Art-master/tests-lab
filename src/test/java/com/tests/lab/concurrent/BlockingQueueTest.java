package com.tests.lab.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingQueueTest {

    public record NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) implements Runnable {
        public void run() {
            try {
                while (true) {
                    Integer number = queue.take();
                    if (number.equals(poisonPill)) {
                        return;
                    }
                    System.out.println("consumer: " + Thread.currentThread().getName() + " result: " + number);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public record NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill,
                                  int poisonPillPerProducer) implements Runnable {

        public void run() {
            try {
                generateNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void generateNumbers() throws InterruptedException {
            for (int i = 0; i < 100; i++) {
                numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            }
            for (int j = 0; j < poisonPillPerProducer; j++) {
                numbersQueue.put(poisonPill);
            }
        }
    }

    @Test
    void consumerProducerTest() {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

        new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }
}
