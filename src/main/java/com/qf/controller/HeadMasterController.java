package com.qf.controller;

import com.qf.pojo.Employee;
import com.qf.pojo.User;
import com.qf.service.HeadMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HeadMasterController {
    @Autowired
    private HeadMasterService headMasterService;

    @RequestMapping("person")
    public String getperson(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        request.setAttribute("person",master);
        return "forms";
    }
}
