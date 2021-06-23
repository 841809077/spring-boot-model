package com.example.thread;

public class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("mythread2 run......");
    }

    public static void main(String[] args) {
        MyThread2 myThread = new MyThread2();
        myThread.run();
    }
}
