/**
 * (Generic parallel merge sort) Revise Listing 32.10 ParallelMergeSort.java, to define a generic
 * parallelMergeSort method as follows:
 *
 * @method public static <E extends Comparable<E>> void parallelMergeSort(E[] list)
 * Create a main() method which will create an array of integers, print the array of integers, then
 * use the parallelMergeSort() on the array, and print the sorted array. Then, repeat the steps for
 * an array of strings.
 * Hints:
 * 1. Change sortTask signature to: private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
 * 2. This line in ParallelMax
 * int[] firstHalf = new int[list.length / 2];
 * will change to E[] firstHalf = (E[])(new Comparable[list.length / 2]); and similarly for secondHalf.
 *
 * @Author: Venkata Lakshmi Sasank Tipparaju
 */
package com.example.homework2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class GenericParallelMergeSort {

    //Main Method
    public static void main(String[] args) {
        Random random = new Random();
        final int SIZE = 10;
        //declare integer and string array
        Integer[] integersList = new Integer[SIZE];
        String[] stringsList = new String[SIZE];
        //initialize integer & string arrays with random values
        for (int i = 0; i < integersList.length; i++) {
            integersList[i] = random.nextInt(-1000, 1001);
            stringsList[i] = getRandomString(4, 11);
        }

        //Display array and perform parallel merge sort
        System.out.println("Integer Array Before Sort: " + Arrays.toString(integersList));
        long startTime = System.currentTimeMillis();
        parallelMergeSort(integersList); // Invoke parallel merge sort
        long endTime = System.currentTimeMillis();
        System.out.println("Integer Array After Sort: " + Arrays.toString(integersList));
        System.out.println("Parallel time with " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");
        System.out.println();

        System.out.println("String Array Before Sort: " + Arrays.toString(stringsList));
        startTime = System.currentTimeMillis();
        parallelMergeSort(stringsList); // Invoke parallel merge sort
        endTime = System.currentTimeMillis();
        System.out.println("String Array After Sort: " + Arrays.toString(stringsList));
        System.out.println("Parallel time with " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");


    }

    //Method to generate random strings of length minLength  and maxLength
    public static String getRandomString(int minLength, int maxLength) {
        Random random = new Random();
        //generating random strings using Random Class
        int stringLength = random.nextInt(minLength, maxLength);
        StringBuilder randomString = new StringBuilder();
        while (stringLength > 0) {
            randomString.append((char)(random.nextInt(0,26)+97));
            stringLength--;
        }
        return randomString.toString();
    }

    //Method to merge the first and second list into temporary list variable
    public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].compareTo(list2[current2]) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    //
    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        //RecursiveAction is a ForkJoinTask subclasses . This task doesn't return any value.
        RecursiveAction mainTask = new SortTask<>(list);
        ForkJoinPool pool = new ForkJoinPool();
        //Performs the given task, returning its result upon completion.
        pool.invoke(mainTask);
    }

    /**
     * SortTask Class that extends RecursiveAction -used to divided and sorting executed parallelly.
     * RecursiveAction class is extended to create a task that has a void return type.
     * The code that represents the computational portion of the task is kept within the compute() method of RecursiveAction.
     */
    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private final int THRESHOLD = 10;
        private E[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        //The main computation of a task on ForkJoinPool.Invoke is performed here.
        @Override
        protected void compute() {
            if (list.length < THRESHOLD)
                java.util.Arrays.sort(list);
            else {
                // Obtain the first half
                E[] firstHalf = (E[]) (new Comparable[list.length / 2]);
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                // Obtain the second half
                int secondHalfLength = list.length - list.length / 2;
                E[] secondHalf = (E[]) (new Comparable[secondHalfLength]);
                System.arraycopy(list, list.length / 2,
                        secondHalf, 0, secondHalfLength);

                // Recursively sort the two halves
                invokeAll(new SortTask<>(firstHalf),
                        new SortTask<>(secondHalf));

                // Merge firstHalf with secondHalf into list
                merge(firstHalf, secondHalf, list);
            }
        }
    }
}
