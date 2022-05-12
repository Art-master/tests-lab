package com.tests.lab.mbeans;

import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.management.*;
import java.beans.ConstructorProperties;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MXBeansTest {

    public interface QueueSamplerMXBean {
        QueueSample getQueueSample();
        void clearQueue();
    }

    @Getter
    public static class QueueSample {

        private final Date date;
        private final int size;
        private final String head;

        @ConstructorProperties({"date", "size", "head"})
        public QueueSample(Date date, int size, String head) {
            this.date = date;
            this.size = size;
            this.head = head;
        }
    }

    public static class QueueSampler
            implements QueueSamplerMXBean {

        private final Queue<String> queue;

        public QueueSampler (Queue<String> queue) {
            this.queue = queue;
        }

        public QueueSample getQueueSample() {
            synchronized (queue) {
                return new QueueSample(new Date(),
                        queue.size(), queue.peek());
            }
        }

        public void clearQueue() {
            synchronized (queue) {
                queue.clear();
            }
        }
    }

    @SneakyThrows
    @Test
    void simpleBeanTest() {
        MBeanServer mbs =
                ManagementFactory.getPlatformMBeanServer();
        ObjectName mxbeanName = new ObjectName("com.example:type=QueueSampler");

        Queue<String> queue = new ArrayBlockingQueue<>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxbean = new QueueSampler(queue);

        mbs.registerMBean(mxbean, mxbeanName);

        System.out.println("Waiting...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
