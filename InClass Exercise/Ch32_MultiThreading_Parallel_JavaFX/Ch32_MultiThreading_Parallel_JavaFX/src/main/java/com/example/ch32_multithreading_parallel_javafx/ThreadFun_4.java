/* Practice 2 - Takes the code from ThreadFun_3
    4.	How could you force one thread to finish before the next thread starts? (
     i.e., thread1 finishes its printing before thread2 starts its printing, thread2
    finishes before thread3 finishes its printing)
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_4 {
    public static void main(String[] args) throws InterruptedException {
        Task4 task1 = new Task4("sunshine", 5);
        Task4 task2 = new Task4("clouds", 25);
        Task4 task3 = new Task4("mules", 50);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("ALL DONE!");
    }
}

class Task4 implements Runnable{
    String word;
    int number;
    public Task4(String word, int number){
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
