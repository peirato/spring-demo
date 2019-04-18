package com.peirato.dome;

import com.peirato.dome.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Test
    public void testList(){
        redisTemplate.opsForList().leftPush("users",new User(1L,"xiaoming","male","2010-1-1",9));
        redisTemplate.opsForList().rightPush("users",new User(2L,"xiaoli","male","2011-1-1",8));
        redisTemplate.opsForList().rightPush("users",new User(3L,"xiaowang","male","2012-1-1",7));
        redisTemplate.opsForList().rightPush("users",new User(4L,"xiaoyang","male","2012-1-1",7));
        redisTemplate.opsForList().rightPush("users",new User(5L,"xiaohei","male","2012-1-1",7));
        redisTemplate.expire("users",2,TimeUnit.MINUTES);
        BoundListOperations<String, User> usersBound = redisTemplate.boundListOps("users");
        usersBound.leftPop();
        usersBound.rightPop();
        p(usersBound.size());
        User user = usersBound.index(1);
        p(user);
        p(usersBound.remove(1,user));
        p(usersBound.range(0,-1));



    }

    private void p(Object msg) {
        System.out.println(msg);
    }
}
