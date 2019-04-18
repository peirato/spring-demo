package com.peirato.dome;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@SuppressWarnings("SpellCheckingInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private JedisPool jedisPool;

    /**
     * String
     */

    @Test
    public void set() {
        p(jedis().set("user1", "hello !"));
    }

    @Test
    public void get() {
        p(jedis().get("user1"));
    }

    @Test
    public void set2() {
        p(jedis().setnx("user1", "hello world !"));
    }

    @Test
    public void setex() throws InterruptedException {
        p(jedis().setex("user2", 1, "hello !"));
        p(jedis().get("user2"));
        Thread.sleep(1100);
        p(jedis().get("user2"));
    }

    @Test
    public void del() {
        p(jedis().del("user1"));
    }

    /**
     * list
     */

    @Test
    public void lpush() {
        p(jedis().lpush("users", "A"));
        p(jedis().lpush("users", "B"));
        p(jedis().lpush("users", "C"));
        p(jedis().lpush("users", "D"));
        p(jedis().lpush("users", "E"));

    }

    @Test
    public void lrange(){
        p(jedis().llen("users"));
        p(jedis().lrange("users",0,-1));
    }

    @Test
    public void lset(){
        p(jedis().lset("users", 0, "xiaoming"));
        p(jedis().lrange("users",0,-1));
    }

    @Test
    public void pop(){
        p(jedis().lpop("users"));
        p(jedis().rpop("users"));
        p(jedis().lrange("users",0,-1));
    }

    /**
     * hash
     * @return
     */

    @Test
    public void hset(){
        p(jedis().hset("user_hash","name","xiaoming"));
        p(jedis().hset("user_hash","gender","male"));
        p(jedis().hset("user_hash","birth","2010-1-1"));
    }

    @Test
    public void hget(){
        p(jedis().hget("user_hash","name"));
        p(jedis().hget("user_hash","gender"));
    }

    @Test
    public void hdel(){
        p(jedis().hdel("user_hash","gender"));
    }

    @Test
    public void hgetall(){
        p(jedis().hgetAll("user_hash"));
        p(jedis().hkeys("user_hash"));
    }

    private Jedis jedis() {
        return jedisPool.getResource();
    }

    private void p(Object msg) {
        System.out.println(msg);
    }
}
