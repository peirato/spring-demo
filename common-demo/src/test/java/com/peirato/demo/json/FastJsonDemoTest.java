package com.peirato.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peirato.demo.json.model.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author peirato.
 * @date 2019/7/9
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastJsonDemoTest {



    //1.json和对象互转
    @Test
    public void jsonAndJavaBean(){
        List<String> hobby = new ArrayList<>();
        hobby.add("吃饭");
        hobby.add("睡觉");
        TestUser user = new TestUser("老王",47,hobby);
        String json = JSON.toJSONString(user);
        System.out.println("json string :" +json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println("jsonObject :" +jsonObject);
        TestUser userNew =  jsonObject.toJavaObject(TestUser.class);
        System.out.println("userNew :" +userNew);
    }

    //2.json和list互转

    @Test
    public void jsonAndArray() {
        List<String> hobby = new ArrayList<>();
        hobby.add("吃饭");
        hobby.add("睡觉");
        List<TestUser> userList = new ArrayList<>();
        userList.add(new TestUser("老王", 47, hobby));
        userList.add(new TestUser("小王", 22, hobby));
        String json = JSON.toJSONString(userList);
        System.out.println("json array string :" + json);
        JSONArray jsonArray = JSON.parseArray(json);
        System.out.println("jsonArray :" + jsonArray);
        List<TestUser> testUsersListNew = jsonArray.toJavaList(TestUser.class);
        System.out.println("testUsersListNew :" + testUsersListNew);

        List<TestUser> testUserStream = jsonArray.stream().map(new Function<Object, TestUser>() {
            @Override
            public TestUser apply(Object o) {
                JSONObject jsonObject = (JSONObject) o;
                return new TestUser(jsonObject.getString("name"),jsonObject.getInteger("age"),null);
            }
        }).collect(Collectors.toList());

        System.out.println("testUserStream :" + testUserStream);

    }
}
