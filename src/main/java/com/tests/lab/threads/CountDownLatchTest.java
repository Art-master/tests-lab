package com.tests.lab.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
                .limit(5)
                .collect(toList());

        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released");

        outputScraper.forEach(System.out::println);

    }

    public record Worker(List<String> outputScraper, CountDownLatch countDownLatch) implements Runnable {

        @Override
        public void run() {
            countDownLatch.countDown();
            outputScraper.add("Counted down " + countDownLatch.getCount());
        }
    }
}
