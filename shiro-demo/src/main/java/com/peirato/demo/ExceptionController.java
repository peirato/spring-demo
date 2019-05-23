package com.peirato.demo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.security.auth.login.AccountException;

/**
 * @author peirato.
 * @date 2019/5/220:06
 * @description: 异常处理
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccountException.class)
    public String handleShiroException(AccountException e){
        return "AccountException:"+e.getMessage();
    }
}
