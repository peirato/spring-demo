package com.peirato.demo.user;

import org.springframework.stereotype.Service;

@Service
public class UserServer {
    public User auth(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new User();
        } else if
            (username.equals("user") && password.equals("123456")){
            return new User();
        } else {
            return null;
        }
    }

    public String getRole(String username) {
        return username;
    }
}
