package com.tests.lab.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    public static class ValueSumCounter extends RecursiveTask<Integer> {

        private final int[] array;

        public ValueSumCounter(int[] array) {
            this.array = array;
        }

        @Override
        protected Integer compute() {
            if(array.length <= 2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Arrays.stream(array).sum();
            }
            ValueSumCounter firstHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, 0, array.length / 2));
            ValueSumCounter secondHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
            firstHalfArrayValueSumCounter.fork();
            secondHalfArrayValueSumCounter.fork();
            return firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
        }
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = 3;
        }
        return array;
    }

    @Test
    void test() {
        int[] array = getInitArray(10000);
        ValueSumCounter counter = new ValueSumCounter(array);
        System.out.println(new Date());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(counter));
        System.out.println(new Date());
    }
}
