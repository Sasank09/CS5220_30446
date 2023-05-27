/* Practice 2- yield() - Starts with ThreadFun_5
1.	Set thread1's priority to the lowest and all the other threads to highest
2.	Run the program, which thread finishes last?
3.	Inside the Task class's run() method's for loop, add an if statement to yield
    the thread if its not thread1 (check that the thread's word is not whatever word
    you gave to thread1, for example)
4.	Which thread finishes first?
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_6 {
    public static void main(String[] args) throws InterruptedException {
        Task6 task1 = new Task6("sunshine", 100);
        Task6 task2 = new Task6("clouds", 25);
        Task6 task3 = new Task6("mules", 50);
        Task6 task4 = new Task6("crackers", 25);
        Task6 task5 = new Task6("cake", 25);
        Task6 task6 = new Task6("pie", 25);
        Task6 task7 = new Task6("salad", 25);
        Task6 task8 = new Task6("soup", 25);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);
        Thread thread5 = new Thread(task5);
        Thread thread6 = new Thread(task6);
        Thread thread7 = new Thread(task7);
        Thread thread8 = new Thread(task8);

        thread1.setPriority(Thread.MAX_PRIORITY);  //sunshine
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.NORM_PRIORITY);
        thread4.setPriority(Thread.NORM_PRIORITY);
        thread5.setPriority(Thread.NORM_PRIORITY);
        thread6.setPriority(Thread.NORM_PRIORITY);
        thread7.setPriority(Thread.NORM_PRIORITY);
        thread8.setPriority(Thread.NORM_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        System.out.println("ALL DONE!");
    }
}

class Task6 implements Runnable {
    String word;
    int number;

    public Task6(String word, int number) {
        this.word = word;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            System.out.println(word);
            if (word.equals("sunshine")) {
                Thread.yield();
            }
        }
        System.out.println(word + " Task is FINISHED!\n");
    }
}
