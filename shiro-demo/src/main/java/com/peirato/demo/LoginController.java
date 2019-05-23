package com.peirato.demo;

import com.peirato.demo.user.UserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author peirato.
 * @date 2019/5/2123:46
 * @description: TODO
 */
@RestController
public class LoginController {

    @Resource
    private UserServer userServer;

    @PostMapping("/login")
    public String login(String username,String password){
        //从SecurityUtils里面创建一个Subject
        Subject subject = SecurityUtils.getSubject();
        //准备认证的token
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登陆认证
        subject.login(token);
        //根据权限获取数据
        String role = userServer.getRole(username);

        if(StringUtils.isEmpty(role)){
            return "登陆失败： 身份认证失败";
        }
        return "登陆成功：你的身份是"+role;
    }

    @GetMapping("/notLogin")
    public String notLogin(){
        return "尚未登陆，请登录";
    }

    @GetMapping("/notRole")
    public String notRole(){
        return "没有权限";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "成功注销";
    }

    /**
     * 游客
     */

    @GetMapping("/guest/enter")
    public String enter() {
        return "游客登陆成功";
    }

    @GetMapping("/guest/message")
    public String guestMessage(){
        return "获取游客消息成功";
    }

    //

    /**
     * 用户
     */
    @GetMapping("/user/message")
    public String getMessage(){
        return "获取用户消息成功";
    }

    /**
     * 管理员
     */
    @GetMapping("/admin/message")
    public String adminMessage(){
        return "获取管理员消息成功";
    }

}
