package com.peirato.demo.config;

import com.peirato.demo.MyConf;
import com.peirato.demo.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfig {

    private ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZK(null);
    }

    @After
    public void close() throws InterruptedException {
        zk.close();
    }

    @Test
    public void getConf(){

        WatcherCallBack watcherCallBack = new WatcherCallBack();

        watcherCallBack.setZk(zk);

        MyConf myConf = new MyConf();

        watcherCallBack.setConf(myConf);

        // 阻塞等待回调
        watcherCallBack.await();

        // 两种情况 ，节点存在和节点不存在

        while (true){

            if("".equals(myConf.getConf())){
                System.out.println("配置位空");
                // 配置失效 重新等待回调
                watcherCallBack.await();
            }else{
                System.out.println("获得配置："+myConf.getConf());
                // 应用配置
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
