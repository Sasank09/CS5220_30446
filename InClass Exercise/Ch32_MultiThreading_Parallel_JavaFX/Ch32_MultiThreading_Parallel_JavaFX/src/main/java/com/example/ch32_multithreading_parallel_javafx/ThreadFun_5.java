/* Practice 2 - Priorities - Starts with code from ThreadFun_4
1.	Give thread1 and thread2 the lowest priority, but thread3 the highest priority
2.	Which thread finishes first? Which finishes last?
3.	Change thread2's priority to normal
4.	What order do the threads finish their tasks?
5.	Create five more threads, giving them each a task of printing a different word
6.	Which thread finishes first? Which finishes last?
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_5 {
    public static void main(String[] args) throws InterruptedException {
        Task5 task1 = new Task5("sunshine", 100);
        Task5 task2 = new Task5("clouds", 25);
        Task5 task3 = new Task5("mules", 50);
       /* Task5 task4 = new Task5("crackers", 25);
        Task5 task5 = new Task5("cake", 25);
        Task5 task6 = new Task5("pie", 25);
        Task5 task7 = new Task5("salad", 25);
        Task5 task8 = new Task5("soup", 25);
        */


        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        //Thread thread4 = new Thread(task4);
        //Thread thread5 = new Thread(task5);
        //Thread thread6 = new Thread(task6);
        //Thread thread7 = new Thread(task7);
        //Thread thread8 = new Thread(task8);

        thread1.start();
        thread2.start();
        thread3.start();
        //thread4.start();
        //thread5.start();
        //thread6.start();
        //thread7.start();
        //thread8.start();

        System.out.println("ALL DONE!");
    }
}

class Task5 implements Runnable{
    String word;
    int number;
    public Task5(String word, int number){
        this.word = word;
        this.number = number;
    }

    @Override
    public void run(){
        for (int i = 0; i < number; i++){
            System.out.println(word);
        }
        System.out.println(word + " Task is FINISHED!\n");
    }
}
