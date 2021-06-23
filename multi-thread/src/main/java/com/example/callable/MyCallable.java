package com.example.callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private int i;

    public MyCallable(int i){
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + "," + i;
    }
}
