//Practice 2 - multiple threads - Take the code from ThreadFun_1 and add:
//  1. Create two threads, each with a different word
//  2. Start these threads after the first thread is started
//  3. Run the program several times, is the output consistent or somewhat different each run?

package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_2 {
    public static void main(String[] args) {
        Task2 task1 = new Task2("sunshine", 25);
        Task2 task2 = new Task2("mules", 25);
        Task2 task3 = new Task2("cloud", 25);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Task2 implements Runnable{
    String word;
    int number;
    public Task2(String word, int number){
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
