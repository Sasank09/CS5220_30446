package com.sasank.project;

import java.util.Random;
import java.util.concurrent.*;
import java.math.BigInteger;

/**
 * This class demonstrates the processing of a two-dimensional array using both sequential and parallel approaches.
 */
public class TwoDimensionalArray {
    private static final int ARRAY_SIZE = 10000;
    private static final int RANDOM_RANGE_MIN = 1;
    private static final int RANDOM_RANGE_MAX = 9000000;

    /**
     * The main method of the program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        int[][] array = new int[ARRAY_SIZE][ARRAY_SIZE];
        Random random = new Random();

        // Populate the array with random numbers
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(RANDOM_RANGE_MAX - RANDOM_RANGE_MIN + 1) + RANDOM_RANGE_MIN;
            }
        }

        System.out.println("Sequential Processing:");
        printTableHeader();
        long startTime = System.currentTimeMillis();
        int sequentialMax = SequentialArrayProcessing.findMax(array);
        long sequentialMaxTime = System.currentTimeMillis() - startTime;
        printTableRow("Find Max", sequentialMax, sequentialMaxTime);

        startTime = System.currentTimeMillis();
        int sequentialMin = SequentialArrayProcessing.findMin(array);
        long sequentialMinTime = System.currentTimeMillis() - startTime;
        printTableRow("Find Min", sequentialMin, sequentialMinTime);

        startTime = System.currentTimeMillis();
        BigInteger sequentialSum = SequentialArrayProcessing.sum(array);
        long sequentialSumTime = System.currentTimeMillis() - startTime;
        printTableRow("Sum", sequentialSum.toString(), sequentialSumTime);

        printTableFooter();
        System.out.println();

        System.out.println("Parallel Processing:");
        printTableHeader();
        startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelFindMaxTask maxTask = new ParallelFindMaxTask(array, 0, ARRAY_SIZE);
        int parallelMax = forkJoinPool.invoke(maxTask);
        long parallelMaxTime = System.currentTimeMillis() - startTime;
        printTableRow("Find Max", parallelMax, parallelMaxTime);

        startTime = System.currentTimeMillis();
        ParallelFindMinTask minTask = new ParallelFindMinTask(array, 0, ARRAY_SIZE);
        int parallelMin = forkJoinPool.invoke(minTask);
        long parallelMinTime = System.currentTimeMillis() - startTime;
        printTableRow("Find Min", parallelMin, parallelMinTime);

        startTime = System.currentTimeMillis();
        ParallelSumTask sumTask = new ParallelSumTask(array, 0, ARRAY_SIZE);
        BigInteger parallelSum = forkJoinPool.invoke(sumTask);
        long parallelSumTime = System.currentTimeMillis() - startTime;
        forkJoinPool.shutdown();
        printTableRow("Sum", parallelSum.toString(), parallelSumTime);

        printTableFooter();
    }

    /**
     * Prints the header of the result table.
     */
    private static void printTableHeader() {
        System.out.println("+--------+-----------------+---------------+");
        System.out.println("|  Task  |   Result        |   Time (ms)   |");
        System.out.println("+--------+-----------------+---------------+");
    }

    /**
     * Prints a row in the result table.
     *
     * @param task   The name of the task.
     * @param result The result of the task.
     * @param time   The execution time of the task.
     */
    private static void printTableRow(String task, Object result, long time) {
        System.out.printf("|%-8s|%17s|%15d|%n", task, result, time);
    }

    /**
     * Prints the footer of the result table.
     */
    private static void printTableFooter() {
        System.out.println("+--------+-----------------+---------------+");
    }
}

/**
 * This class contains sequential processing methods for a two-dimensional array.
 */
class SequentialArrayProcessing {
    /**
     * Finds the maximum value in the two-dimensional array.
     *
     * @param array The input array.
     * @return The maximum value in the array.
     */
    public static int findMax(int[][] array) {
        int max = Integer.MIN_VALUE;
        for (int[] row : array) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    /**
     * Finds the minimum value in the two-dimensional array.
     *
     * @param array The input array.
     * @return The minimum value in the array.
     */
    public static int findMin(int[][] array) {
        int min = Integer.MAX_VALUE;
        for (int[] row : array) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    /**
     * Computes the sum of all values in the two-dimensional array.
     *
     * @param array The input array.
     * @return The sum of all values in the array.
     */
    public static BigInteger sum(int[][] array) {
        BigInteger sum = BigInteger.ZERO;
        for (int[] row : array) {
            for (int value : row) {
                sum = sum.add(BigInteger.valueOf(value));
            }
        }
        return sum;
    }
}

/**
 * This class represents a task that finds the maximum value in a sub-array of a two-dimensional array using parallel processing.
 */
class ParallelFindMaxTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 100;
    private final int[][] array;
    private final int startRow;
    private final int endRow;

    /**
     * Creates a new ParallelFindMaxTask.
     *
     * @param array    The input array.
     * @param startRow The starting row index of the sub-array.
     * @param endRow   The ending row index of the sub-array.
     */
    public ParallelFindMaxTask(int[][] array, int startRow, int endRow) {
        this.array = array;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * Computes the maximum value in the sub-array.
     *
     * @return The maximum value in the sub-array.
     */
    @Override
    protected Integer compute() {
        if (endRow - startRow <= THRESHOLD) {
            int max = Integer.MIN_VALUE;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] > max) {
                        max = array[i][j];
                    }
                }
            }
            return max;
        } else {
            int mid = startRow + (endRow - startRow) / 2;
            ParallelFindMaxTask leftTask = new ParallelFindMaxTask(array, startRow, mid);
            ParallelFindMaxTask rightTask = new ParallelFindMaxTask(array, mid, endRow);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return Math.max(leftResult, rightResult);
        }
    }
}

/**
 * This class represents a task that finds the minimum value in a sub-array of a two-dimensional array using parallel processing.
 */
class ParallelFindMinTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 100;
    private final int[][] array;
    private final int startRow;
    private final int endRow;

    /**
     * Creates a new ParallelFindMinTask.
     *
     * @param array    The input array.
     * @param startRow The starting row index of the sub-array.
     * @param endRow   The ending row index of the sub-array.
     */
    public ParallelFindMinTask(int[][] array, int startRow, int endRow) {
        this.array = array;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * Computes the minimum value in the sub-array.
     *
     * @return The minimum value in the sub-array.
     */
    @Override
    protected Integer compute() {
        if (endRow - startRow <= THRESHOLD) {
            int min = Integer.MAX_VALUE;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] < min) {
                        min = array[i][j];
                    }
                }
            }
            return min;
        } else {
            int mid = startRow + (endRow - startRow) / 2;
            ParallelFindMinTask leftTask = new ParallelFindMinTask(array, startRow, mid);
            ParallelFindMinTask rightTask = new ParallelFindMinTask(array, mid, endRow);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return Math.min(leftResult, rightResult);
        }
    }
}

/**
 * This class represents a task that computes the sum of all values in a sub-array of a two-dimensional array using parallel processing.
 */
class ParallelSumTask extends RecursiveTask<BigInteger> {

    private static final int THRESHOLD = 100;
    private final int[][] array;
    private final int startRow;
    private final int endRow;

    /**
     * Creates a new ParallelSumTask.
     *
     * @param array    The input array.
     * @param startRow The starting row index of the sub-array.
     * @param endRow   The ending row index of the sub-array.
     */
    public ParallelSumTask(int[][] array, int startRow, int endRow) {
        this.array = array;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * Computes the sum of all values in the sub-array.
     *
     * @return The sum of all values in the sub-array.
     */
    @Override
    protected BigInteger compute() {
        if (endRow - startRow <= THRESHOLD) {
            BigInteger sum = BigInteger.ZERO;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    sum = sum.add(BigInteger.valueOf(array[i][j]));
                }
            }
            return sum;
        } else {
            int mid = startRow + (endRow - startRow) / 2;
            ParallelSumTask leftTask = new ParallelSumTask(array, startRow, mid);
            ParallelSumTask rightTask = new ParallelSumTask(array, mid, endRow);
            leftTask.fork();
            BigInteger rightResult = rightTask.compute();
            BigInteger leftResult = leftTask.join();
            return leftResult.add(rightResult);
        }
    }
}
