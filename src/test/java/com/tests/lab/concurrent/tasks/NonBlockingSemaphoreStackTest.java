package com.tests.lab.concurrent.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Неблокирующий стек на Semaphore
 */
public class NonBlockingSemaphoreStackTest {
    private static class Stack<T> {
        private Element<T> head = null;
        private final Semaphore semaphore = new Semaphore(1);

        public Stack<T> push(T value) {
            try {
                //Запрашиваем разрешение и ждем что бы не случилось
                semaphore.acquireUninterruptibly();
                final Element<T> current = new Element<>();
                current.value = value;
                current.previous = head;
                head = current;
            } finally {
                semaphore.release();
            }
            return this;
        }

        public T pop() {
            Element<T> current;
            try {
                //Запрашиваем разрешение и ждем что бы не случилось
                semaphore.acquireUninterruptibly();
                current = head;
                if (current == null) {
                    return null;
                }
                head = current.previous;
            } finally {
                semaphore.release();
            }
            return current.value;
        }

        public int size() {
            Element<T> element = head;
            int size = 0;
            while (element != null) {
                size++;
                element = element.previous;
            }
            return size;
        }

        private static class Element<T> {
            private T value;
            private Element<T> previous;
        }
    }


    @Test
    @DisplayName("Тест стека на построенного на Semaphore")
    void stackTest() {
        int threadNum = 100;
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        Stack<Integer> stack = new Stack<>();
        Integer integer = 1;
        for (int i = 0; i < threadNum; i++) {
            service.submit(() -> stack.push(integer));
        }

        //Ждем пока все предыдущие потоки запишут данные
        while (stack.size() != threadNum) ;

        for (int i = 0; i < threadNum; i++) {
            service.submit(stack::pop);
        }

        //Убиваем сервис
        service.shutdown();

        //Ждем пока все потоки сервиса выполнятся и сервис будет убит
        while (!service.isTerminated()) ;

        Assertions.assertEquals(0, stack.size());
    }
}
