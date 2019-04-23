package com.peirato.dome;

import com.peirato.dome.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Resource private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testString() throws InterruptedException {

        redisTemplate.opsForValue().set("user1",new User(1L,"xiaoming","male","2010-1-1",9),2, TimeUnit.SECONDS);

        p(redisTemplate.opsForValue().get("user1"));

        p(redisTemplate.opsForValue().setIfAbsent("user1",new User(1L,"xiaoming","male","2010-1-1",9)));

        p(redisTemplate.opsForValue().setIfAbsent("user2",new User(2L,"xiaoli","male","2011-1-1",8)));

        p(redisTemplate.expire("user2",2,TimeUnit.SECONDS));

        p(redisTemplate.opsForValue().get("user2"));

        Thread.sleep(2000);

        p(redisTemplate.opsForValue().multiGet(Arrays.asList(new String[]{"user1","user2"})));
    }

    private void p(Object msg) {
        System.out.println(msg);
    }
}
