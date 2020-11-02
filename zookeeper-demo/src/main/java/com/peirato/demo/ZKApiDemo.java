package com.peirato.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKApiDemo {

    public static final String ZK_SERVICES =
            "192.168.234.101:2181,192.168.234.102:2181,192.168.234.103:2181,192.168.234.104:2181";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        final CountDownLatch cd = new CountDownLatch(1);

        final ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVICES, 3000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

                System.out.println(" process..  watchedEvent = "+ watchedEvent.getState().name());
                switch (watchedEvent.getState()) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        // 连接完成时解锁
                        cd.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                }

            }
        });

        // 由于是异步获取 需要先阻塞线程 等拿到回调数据再放继续
        cd.await();
        ZooKeeper.States state = zooKeeper.getState();

        switch (state) {
            case CONNECTING:
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }


        // 创建节点
        zooKeeper.create("/demo","demo_data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        final Stat stat = new Stat();

        byte[] data = zooKeeper.getData("/demo", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData Watcher :" + watchedEvent.toString());
                // 重新注册
                try {
                    zooKeeper.getData("/demo", this, stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, stat);

        System.out.println(new String(data));

        // 触发回调
        Stat stat1 = zooKeeper.setData("/demo", "newData".getBytes(), 0);

        Stat stat2 = zooKeeper.setData("/demo", "newData1".getBytes(), stat1.getVersion());

        // 异步api
        System.out.println("异步api开始");
        zooKeeper.getData("/demo", false, new AsyncCallback.DataCallback(){
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("异步回调");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        },"abc");

        System.out.println("异步api结束");

        Thread.sleep(Integer.MAX_VALUE);
    }






}
