/* Practice 2 - Thread pools -
Write a program that creates 1000 threads.
Make a task for adding numbers from 0 up to some specified number
(like 10,000,000 or a random number in this range) and give each thread such a task.
Inside the task's run() method, print out the thread's number and
the sum that it added up. Note that adding large integers may require using long.

Sample output might look something like this:
Thread 1 added up from 0 to 494568: 122299000596
Thread 0 added up from 0 to 3617869: 6544489859515
Thread 3 added up from 0 to 8909699: 39691372590150
Thread 2 added up from 0 to 1138884: 648528952170
Thread 63 added up from 0 to 1864713: 1738578218541
Thread 66 added up from 0 to 4916349: 12085246203075
Thread 68 added up from 0 to 7802764: 30441566921230
Thread 153 added up from 0 to 1148551: 659585274076
...

 */
package com.example.ch32_multithreading_parallel_javafx;

import java.util.Random;

public class ThreadFun_8 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        //create an array of 1000 numbers
        Thread[] bunchOfThreads = new Thread[1000];
        Random random = new Random();

        //for loop to initialize the threads
        for (int i = 0; i < 1000; i++) {
            int x = random.nextInt(10000000);
            bunchOfThreads[i] = new Thread(new SumIt(x, i));
        }

        for (int i = 0; i < 1000; i++) {
            bunchOfThreads[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            bunchOfThreads[i].join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n***** Parallel time with " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");
    }
}

class SumIt implements Runnable {
    long endValue;
    int threadNum;

    public SumIt(long x, int threadNum) {
        this.endValue = x;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        long total = 0;
        for (int i = 0; i < endValue; i++) {
            total += 1;
        }
        System.out.println("Thread "+threadNum+" added up from 0 to "+endValue +": "+total);
    }
}
