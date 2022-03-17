package com.tests.lab.threads;

public class Deadlock {
    Data1 data1 = new Data1();
    Data2 data2 = new Data2();
    Thread1 thread1 = new Thread1();
    Thread2 thread2 = new Thread2();
    ThreadMonitoring threadMonitoring = new ThreadMonitoring();


    public void create() {

        thread1.setName("thread1");
        thread2.setName("thread2");


        thread1.start();
        thread2.start();
        threadMonitoring.start();
    }


    private class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("Thread 1 started");
            data1.set(2000);
            data2.set(2);
        }
    }

    private class Thread2 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("Thread 2 started");
            data2.set(1000);
            data1.set(1);
        }
    }

    private class ThreadMonitoring extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(2000);
                System.out.println(thread1.getName() + " " + thread1.getState());
                System.out.println(thread2.getName() + " " + thread2.getState());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private class Data1 {
        public synchronized void set(int num) {
            try {
                Thread.sleep(1000);
                System.out.println("execute set in Data1 by " + Thread.currentThread().getName());
                data2.set(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Data2 {
        public synchronized void set(int num) {
            try {
                Thread.sleep(num);
                System.out.println("execute set in Data2 by " + Thread.currentThread().getName());
                data1.set(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
