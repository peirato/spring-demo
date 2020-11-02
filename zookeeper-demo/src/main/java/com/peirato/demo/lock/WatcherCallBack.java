package com.peirato.demo.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatcherCallBack implements AsyncCallback.StringCallback, AsyncCallback.Children2Callback ,Watcher, AsyncCallback.StatCallback {

    private ZooKeeper zk;

    private String threadName;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public void setZK(ZooKeeper zk) {
        this.zk = zk;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }


    public void tryLock() {

        System.out.println("threadName:" + threadName);
        zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "abc");
        // 阻塞
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void unlock() {
        try {
            zk.delete(pathName,-1);
            System.out.println(pathName +" 释放锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    // zk.create callback
    public void processResult(int rc, String path, Object ctx, String name) {

        if (name != null) {
            System.out.println(threadName + " created node :" + name);
            pathName = name;
            zk.getChildren("/", false, this, "sdf");
        }

    }

    // zk.getChildren
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        // 获取子节点列表 只有第一个才能拿到锁

        Collections.sort(children);

        int i = children.indexOf(pathName.substring(1));

        // 判断是不是第一个
        if (i == 0) {

            // 是
            System.out.println(threadName + " is first");

            // 设置数据
            try {
                zk.setData("/", threadName.getBytes(), -1);
                countDownLatch.countDown();

            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else {
            //不是第一个 监控前一个节点是否存在
            zk.exists("/" + children.get(i - 1),this,this,"sdf");
        }


    }

    public void process(WatchedEvent event) {

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                // 当前一个节点被删除时 获得通知 重新走获取子节点的逻辑
                zk.getChildren("/",false,this ,"sdf");
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
//            case DataWatchRemoved:
//                break;
//            case ChildWatchRemoved:
//                break;
//            case PersistentWatchRemoved:
//                break;
        }

    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {

    }
}
