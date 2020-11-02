package com.peirato.demo;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private static final String ZK_SERVICES =
            "192.168.234.101:2181,192.168.234.102:2181,192.168.234.103:2181,192.168.234.104:2181";
    private static ZooKeeper zooKeeper;

    private static CountDownLatch cd = new CountDownLatch(1);

    private static DefaultWatcher watcher = new DefaultWatcher();


    public static ZooKeeper getZK(String path){

        try {
            zooKeeper = new ZooKeeper(path==null ? ZK_SERVICES:ZK_SERVICES+path,1000,watcher);
            watcher.setCd(cd);
            cd.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zooKeeper;
    }
}
