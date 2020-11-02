package com.peirato.demo.config;

import com.peirato.demo.MyConf;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatcherCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private ZooKeeper zk;
    private MyConf conf;
    private CountDownLatch cd = new CountDownLatch(1);

    public static final String APP_CONF_PATH ="/AppConf";

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }


    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    public void await(){
        zk.exists(APP_CONF_PATH,this,this,"ABC");
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // watcher
    public void process(WatchedEvent watchedEvent) {
        // 节点创建 或者节点数据变化时 重新获取配置
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData(APP_CONF_PATH,this,this,"BCD");
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                zk.getData(APP_CONF_PATH,this,this,"BCD");
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

    // zk.getData callback
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if(data!=null){
            // 当获取到数据时 设置到配置并释放锁
            conf.setConf(new String(data));
            cd.countDown();
        }

    }

    // zk.exist callback
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        // 节点存在 获取配置
        if(stat!=null){
            zk.getData(APP_CONF_PATH,this,this,"BCD");
        }

    }
}
