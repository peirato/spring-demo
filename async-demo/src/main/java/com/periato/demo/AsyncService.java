package com.periato.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Async
    public void asyncRun(int i) {
        System.out.println(Thread.currentThread().getName() + "-" + i);
    }

    @Async
    public Future<Integer> asyncCallbalckRun(int i) {
        System.out.println(Thread.currentThread().getName() + " - " + i);
        return new AsyncResult<Integer>(i);
    }


}
