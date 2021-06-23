package com.example.thread;

public class MyThread1 extends Thread{

    @Override
    public void run() {
        System.out.println("mythread1 run......");
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
    }
}
