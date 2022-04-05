package com.tests.lab.threads.tasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Неблокирующий вывод чисел кратных двум
 */
public class NumbersOfTwoTest {

    static class PowerOfTwo {
        private final AtomicReference<BigInteger> current = new AtomicReference<>(null);

        BigInteger next() {
            BigInteger recent, next;
            do {
                recent = current.get();
                next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
            } while (!current.compareAndSet(recent, next));
            return next;
        }
    }


    @Test
    @DisplayName("Неблокирующий вывод чисел кратных двум")
    void stackTest() throws ExecutionException, InterruptedException {
        int threadNum = 10;
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        PowerOfTwo power = new PowerOfTwo();

        List<Future<BigInteger>> futures = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) {
            futures.add(service.submit(power::next));
        }

        //Убиваем сервис
        service.shutdown();

        TreeSet<BigInteger> nums = new TreeSet<>();
        for (Future<BigInteger> future : futures) {
            nums.add(future.get());
        }
        nums.forEach(System.out::println);
    }
}
