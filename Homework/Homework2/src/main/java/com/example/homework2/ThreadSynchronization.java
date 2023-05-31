/**
 * (Synchronized threads) Write a program that launches 1000 threads by using a fixed thread pool.
 * Each thread adds 1 to a variable sum that is initially 0. You need to pass sum by reference to each thread.
 * sum is declared global to the program as is type Integers--
 * In order to pass it by reference, define an Integer wrapper object to hold sum.
 * Run the program with and without synchronization to see its effect.
 *
 * @Author: Venkata Lakshmi Sasank Tipparaju (700738838)
 */
package com.example.homework2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSynchronization {

    private static Integer sum = Integer.valueOf(0);

    public static void main(String[] args) {
        //Initialize ExecutorService instance with newFixedThreadPool() method creates an executor with a maximum number(1000) threads at any time
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1000; i++) {
            //get the runnable object and perform the task by calling execute method
            executorService.execute(new SumIt());
        }
        //shutdown the executorService
        executorService.shutdown();
        //loop through a list of tasks that are waiting to be processed, so all tasks will be processed before shutdown
        while (!executorService.isTerminated()) {

        }

        System.out.println("The value of Sum after launching 1000 threads: " + sum);
    }

    //Runnable class object - to perform a task
    private static class SumIt implements Runnable {
        //run method contains the code that is executed inside the new thread
        @Override
        public void run() {
            addByOne();
        }

        /**
         * To work without synchronized remove the keyword synchronized below.
         * Synchronized keyword locks the resources to a thread so that no other thread can access it at a time
         * returns 1000 when synchronized else some random value.
         */
        public static synchronized void addByOne() {
            System.out.println(sum);
            sum = sum + 1;
            // This delay is deliberately added to magnify the
            // data-corruption problem and make it easy to see.
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
            }

        }
    }
}
