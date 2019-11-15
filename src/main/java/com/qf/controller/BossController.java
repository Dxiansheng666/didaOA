package com.qf.controller;

import com.qf.pojo.Employee;
import com.qf.pojo.Employee_Holiday;
import com.qf.pojo.Student_Holiday;
import com.qf.pojo.User;
import com.qf.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BossController {
    @Autowired
    private BossService bossService;
    @RequestMapping("getBossInfo")
    public String getBossInfo(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        Employee employee = bossService.getBossInfo(user.getUid());
        request.setAttribute("employee",employee);
        return "forms";

    }
    @RequestMapping("updateBossUpwd")
    public String updateUpwd(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int i = bossService.updateUpwd(user.getUid());
        if(i>0){
            return "forms";
        }
        return "redirect:updateUpwd";
    }
    @RequestMapping("agreeholiday")
    public String updateholiday(int hid,HttpSession session){
        User user= (User) session.getAttribute("user");
        bossService.updateEmployee_Holiday(hid,1,user.getUname());
        bossService.updateStudent_holiday(hid,1,user.getUname());
        return "manager_holiday";
    }
    @RequestMapping("updateholiday")
    public String getAppHoliday(HttpServletRequest request,HttpSession session){
        User user= (User) session.getAttribute("user");
        List<Student_Holiday> student_holidayList=bossService.getStudent_HolidayList(user.getUname());
        List<Employee_Holiday> employee_holidayList=bossService.getEmployee_HolidayList(user.getUname());
        for (Student_Holiday student_holiday:student_holidayList){
            System.out.println(student_holiday);
        }
        for (Employee_Holiday employee_holiday:employee_holidayList){
            System.out.println(employee_holiday);
        }
        request.setAttribute("student_holidayList",student_holidayList);
        request.setAttribute("employee_holidayList",employee_holidayList);
return "manager_holiday";
    }


}
