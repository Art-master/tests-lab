package com.tests.lab.concurrent.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Неблокирующий стек на AtomicReference
 */
public class NonBlockingStackTest {
    private static class Stack<T> {
        private final AtomicReference<Element<T>> head = new AtomicReference<>();

        public Stack<T> push(T value) {
            final Element<T> current = new Element<>();
            current.value = value;

            Element<T> recent;
            do {
                recent = head.get();
                current.previous = recent;
            } while (!head.compareAndSet(recent, current));
            return this;

        }

        public T pop() {
            Element<T> result;
            Element<T> previous;
            do {
                result = head.get();
                if (result == null) {
                    return null;
                }
                previous = result.previous;
            } while (!head.compareAndSet(result, previous));
            return result.value;
        }

        public int size() {
            Element<T> element = head.get();
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
    @DisplayName("Тест стека на построенного на AtomicReference")
    void stackTest() {
        int threadNum = 100;
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        Stack<Integer> stack = new Stack<>();
        Integer integer = 1;
        for (int i = 0; i < threadNum; i++) {
            service.submit(() -> stack.push(integer));
        }
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
