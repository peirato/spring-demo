package com.peirato.demo.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro 路径映射
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置，默认寻找web工程目录下的"/login.jsp"或者"/login"映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        //无权限是跳转的url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        //设置拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**","anon");
        //用户，需要角色权限 user
        filterChainDefinitionMap.put("/user/**","roles[user]");
        //管理员，需要角色权限 admin
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login","anon");
        //其他接口全部拦截，这段代码必须放在最后
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     * @param customRealm 自定义身份认证 realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDao());
        return mySessionManager;
    }

    @Bean
    public SessionDAO redisSessionDao() {
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setR

    }


    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }



}
