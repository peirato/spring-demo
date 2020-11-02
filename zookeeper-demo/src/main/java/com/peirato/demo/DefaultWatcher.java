package com.peirato.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DefaultWatcher implements Watcher {
    private CountDownLatch cd;

    public void setCd(CountDownLatch cd){
        this.cd = cd;
    }

    public void process(WatchedEvent event) {


        System.out.println("DefaultWatcher.process: state="+event.getState());

        switch (event.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
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
}
