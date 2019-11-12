package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.util.MD5Utils;
import com.qf.util.Pinyinutils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 14:49
 */
@Controller
public class UserController {
    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private UserService userService;

    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("login")
    public String login(String uname,String upwd, HttpSession session){
        String md5Upwd = MD5Utils.md5(upwd);
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(uname,md5Upwd);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                User user = userService.getUserByUname(uname);
                session.setAttribute("user",user);
                return "redirect:index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 添加学生
     */
    @RequestMapping("addStudent")
    public String addStudent(String uname){
        String pinyin = Pinyinutils.getPingYin(uname);
        String upwd = MD5Utils.md5("123456");
        userService.addUser(pinyin,upwd,"学生");
        return "index";
    }
    /**
     * 添加其他角色
     */
    @RequestMapping("addUser")
    public String addUser(String uname,String rolename){
        String pinyin = Pinyinutils.getPingYin(uname);
        String upwd = MD5Utils.md5("123456");
        userService.addUser(uname,upwd,rolename);
        return "index";
    }

}
