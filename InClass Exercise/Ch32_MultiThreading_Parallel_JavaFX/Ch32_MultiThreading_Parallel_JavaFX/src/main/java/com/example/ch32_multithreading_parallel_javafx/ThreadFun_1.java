/*  Practice 2
1.	Create a public class called ThreadFun
2.	Put a main() method in the class
3.	Create a non-public class called Task
1.	A ﬁle can only have one public class, but it's alright to put additional non-public classes
4.	Have Task implement the Runnable interface
5.	Give the Task class two instance variables: a String called word and an int called number
6.	Make Task's constructor such that a string and an int can be passed in to set the instance variables
7.	Implement Task's run() method to print word number amount of times
8.	At the end of run(), print out a message to indicate that the thread has ﬁnished its task
9.	In ThreadFun's main() method, create a Task object (say, task1)
10.	Next, create a Thread object (say, thread1), giving it the Task object
11.	Add a call to start() the Thread object
 */
package com.example.ch32_multithreading_parallel_javafx;

public class ThreadFun_1 {

    public static void main(String[] args) {
        com.example.ch32_multithreading_parallel_javafx.Task task1 = new com.example.ch32_multithreading_parallel_javafx.Task("sunshine", 5);

        Thread thread1 = new Thread(task1);

        thread1.start();
    }
}

class Task implements Runnable {
    String word;
    int number;

    public Task(String word, int number) {
        this.word = word;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            System.out.println(word);
        }
        System.out.println("Task is FINISHED!\n");
    }
}

