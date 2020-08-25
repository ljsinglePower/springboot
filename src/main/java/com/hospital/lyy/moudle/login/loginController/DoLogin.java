package com.hospital.lyy.moudle.login.loginController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoLogin {

    //@ResponseBody
    @PostMapping("/doLogin")
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // rememberme
        token.setRememberMe(true);
        try {
            subject.login(token);
            System.out.println("登录成功!");
            return "success";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
            return "error";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    @RequiresPermissions("select")
    public String list() {
        return "list";
    }

    @GetMapping("/view")
    public String view() {
        return "view";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "noauth";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限认证失败";
    }
}