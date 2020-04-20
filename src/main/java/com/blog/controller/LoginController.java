package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {
    @Autowired
    private UserService service;

    /**
     * 登陆页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username")String username,
                            @RequestParam("password")String password,
                            HttpServletRequest request) {
        User user = this.service.loginUser(username,password);
        if(user==null){
            return "login";
        }else{
            request.getSession().setAttribute("username",user.getUsername());
            return "redirect:admin1";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:admin1";
    }
}