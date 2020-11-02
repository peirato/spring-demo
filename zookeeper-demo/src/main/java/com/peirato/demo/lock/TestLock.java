package com.peirato.demo.lock;

import com.peirato.demo.ZKUtils;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLock {

    private ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZK("/testLock");
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock() {

        // 模拟10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {


                    WatcherCallBack watcherCallBack = new WatcherCallBack();
                    watcherCallBack.setZK(zk);

                    String threadName = Thread.currentThread().getName();
                    watcherCallBack.setThreadName(threadName);

                    // 获得锁
                    watcherCallBack.tryLock();

                    // 干活
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 释放锁
                    watcherCallBack.unlock();

                }
            }).start();
        }

        while (true){

        }


    }
}
