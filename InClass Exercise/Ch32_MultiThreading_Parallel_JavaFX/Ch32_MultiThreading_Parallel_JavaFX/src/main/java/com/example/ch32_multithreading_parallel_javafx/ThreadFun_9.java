/* Practice 2 - cached thread pool - Starts with code from ThreadFun_8 and:
    1. use a cached thread pool instead of individual threads.
 */
package com.example.ch32_multithreading_parallel_javafx;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFun_9 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        //create an array of 1000 numbers
//        Thread[] bunchOfThreads = new Thread[1000];

        //Cached ThreadPool
//        ExecutorService executor = Executors.newCachedThreadPool();

        ExecutorService executor = Executors.newFixedThreadPool(100);

        Random random = new Random();

        //for loop to initialize the threads
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(10000000);
//            bunchOfThreads[i] = new Thread(new SumIt2(x, i));
            executor.execute(new SumIt2(x, i));
        }

//
//        for (int i = 0; i < 1000; i++) {
//            bunchOfThreads[i].start();
//        }
//        for (int i = 0; i < 1000; i++) {
//            bunchOfThreads[i].join();
//        }

        executor.shutdown();

        while (!executor.isTerminated()) {

        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n***** Parallel time with " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");
    }
}

class SumIt2 implements Runnable {
    long endValue;
    int threadNum;

    public SumIt2(long x, int threadNum) {
        this.endValue = x;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        long total = 0;
        for (int i = 0; i < endValue; i++) {
            total += 1;
        }
        System.out.println("Thread " + threadNum + " added up from 0 to " + endValue + ": " + total);
    }
}