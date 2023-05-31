/**
 * (Parallel Sum) Implement the following method using Fork/Join to find the sum of a list.
 * public static double parallelSum(double[] list)
 * Write a test program that finds the sum in a list of 9,000,000 double values.
 * Also create a sequential sum method called sequentialSum(). In the main() method, time how
 * long it takes for each sum method to run and display the time for each. Also, display the sum
 * (result) from each sum method. (See ParallelMax.java for how to track time and on how to
 * declare and initialize each element of an array of 9,000,000 double values to 1.)
 *
 * @Author: Venkata Lakshmi Sasank Tipparaju
 */
package com.example.homework2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSum {
    public static void main(String[] args) {
        // Create a list
        final int SIZE = 9000000;
        double[] list = new double[SIZE];
        Arrays.fill(list, 1.0);

        long startTime = System.currentTimeMillis();
        System.out.println("\nThe Parallel Sum of all elements in double array is " + parallelSum(list));
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel Time with Number of processors " + Runtime.getRuntime().availableProcessors()+ " is " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        System.out.println("\nThe Sequential Sum of all elements in double array is " + sequentialSum(list));
        endTime = System.currentTimeMillis();
        System.out.println("Sequential Time with Number of processors " + Runtime.getRuntime().availableProcessors()+ " is " + (endTime - startTime) + " milliseconds");
    }

    public static Double sequentialSum(double[] list) {
        double totalValue = 0.0;
        for(Double element: list) {
            totalValue+=element;
        }
        return totalValue;
    }

    public static Double parallelSum(double[] list) {
        RecursiveTask<Double> task = new SummationTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class SummationTask extends RecursiveTask<Double> {
        private final static int THRESHOLD = 500;
        private double[] list;
        private int low;
        private int high;

        public SummationTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        public Double compute() {
            if (high - low <= THRESHOLD) {
                double totalSum = 0.0;
                for(int i=low;i<high;i++) {
                    totalSum+= list[i];
                }
                return  totalSum;
            }
            else {
                double totalSum = 0.0;
                int mid =low + ((high - low) / 2);
                RecursiveTask<Double> left = new SummationTask(list, low, mid);
                RecursiveTask<Double> right = new SummationTask(list, mid, high);

                left.fork();

                double rightAns = ((SummationTask) right).compute();
                double leftAns = left.join();


                System.out.println("Sum for the current task: "+(leftAns+rightAns));

                return leftAns+rightAns;
            }
        }
    }
}
