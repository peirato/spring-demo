package com.peirato.dome;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private JedisPool jedisPool;

//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mvc;
//    private MockHttpSession session;

//    @Before
//    public void setupMockMvc(){
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
//        session = new MockHttpSession();
//    }

//    @Test
//    public void test(){
//        System.out.println(domeController.test());
//    }

    @Test
    public void set() {
        Jedis jedis = jedisPool.getResource();
        p(jedis.set("user1", "hello !"));
    }

    private void p(String msg){
        System.out.println(msg);
    }
}
