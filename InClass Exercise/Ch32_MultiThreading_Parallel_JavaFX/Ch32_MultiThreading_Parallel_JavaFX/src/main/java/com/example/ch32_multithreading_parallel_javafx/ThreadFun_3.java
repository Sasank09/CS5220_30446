/*  Practice 2 - join()- Take the code from ThreadFun_2 and add:
1.	From the main() method, print out the message "All Done!" after the threads have
    finished printing their words
2.	What happens if you put the println() call after starting the methods?
3.	How could you force the main() method to wait and do the println() call
    after each of the other threads has finished?
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_3 {
    public static void main(String[] args) throws InterruptedException {
        Task3 task1 = new Task3("sunshine", 25);
        Task3 task2 = new Task3("clouds", 25);
        Task3 task3 = new Task3("mules", 25);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();


        System.out.println("ALL FINISHED");
    }
}

class Task3 implements Runnable{
    String word;
    int number;
    public Task3(String word, int number){
        this.word = word;
        this.number = number;
    }

    @Override
    public void run(){
        for (int i = 0; i < number; i++){
            System.out.println(word);
        }
        System.out.println(word+" Task is FINISHED!\n");
    }
}
