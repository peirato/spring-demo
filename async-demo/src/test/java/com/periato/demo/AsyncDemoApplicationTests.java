package com.periato.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncDemoApplicationTests {

    @Resource(name = "taskExecutor")
    private ExecutorService executorService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getThread() throws InterruptedException {

        for (int i = 0; i < 30; i++) {
            final int i1 = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"-"+i1);
                }
            });
            TimeUnit.MILLISECONDS.sleep(200);

        }

        executorService.shutdown();
    }

    @Resource
    private AsyncService asyncService;

    @Test
    public void testAsyncService() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            asyncService.asyncRun(i);
            TimeUnit.MILLISECONDS.sleep(100);
        }

    }

    @Test
    public void testAsyncServiceCallback() throws ExecutionException, InterruptedException {
        int count =0;
        for (int i = 0; i < 30; i++) {
            count += asyncService.asyncCallbalckRun(i).get();
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("count :"+count);
    }

}
