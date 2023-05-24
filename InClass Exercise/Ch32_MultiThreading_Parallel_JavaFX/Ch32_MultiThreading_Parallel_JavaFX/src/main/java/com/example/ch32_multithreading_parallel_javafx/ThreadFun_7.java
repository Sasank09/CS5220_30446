/* Practice 2 - sleep() - Starts with code from ThreadFun6 and adds:
1.	Change the yield call to a sleep call
2.	Which thread finishes first?
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_7 {
    public static void main(String[] args) throws InterruptedException {
        Task7 task1 = new Task7("sunshine", 100);
        Task7 task2 = new Task7("clouds", 25);
        Task7 task3 = new Task7("mules", 50);
        Task7 task4 = new Task7("crackers", 25);
        Task7 task5 = new Task7("cake", 25);
        Task7 task6 = new Task7("pie", 25);
        Task7 task7 = new Task7("salad", 25);
        Task7 task8 = new Task7("soup", 25);

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

class Task7 implements Runnable{
    String word;
    int number;
    public Task7(String word, int number){
        this.word = word;
        this.number = number;
    }

    @Override
    public void run(){
        for (int i = 0; i < number; i++){
            if(word.equals("sunshine"))
                Thread.yield();
            System.out.println(word);
        }
        System.out.println(word + " Task is FINISHED!\n");
    }
}
