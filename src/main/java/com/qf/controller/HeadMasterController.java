package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.HeadMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HeadMasterController {
    @Autowired
    private HeadMasterService headMasterService;

    /*
    查看个人资料
     */
    @RequestMapping("person")
    public String getperson(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        request.setAttribute("person",master);
        return "forms";
    }
    /*
    修改密码
     */
    @RequestMapping("updateupwd")
    public String updateupwd(String upwd,HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setUpwd(upwd);
        int i = headMasterService.updateUpwdByUid(user);
        if (i>0){
            return "index";
        }
        return "redirect:person";
    }
    /*
    查看所有周报
     */
    @RequestMapping("weekreport")
    public String getweekreport(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        List<WeekReport> weekReportList = headMasterService.getWeekReportListByUid(user.getUid());
        request.setAttribute("weekReport",weekReportList);
        return "weekreport";
    }
    /*
    获取待审批假条
     */
    @RequestMapping("studentHoliday")
    public String shenpi(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        List<Student_Holiday> studentHolidayList = headMasterService.getStudentHolidayByUid(user.getUid());
        request.setAttribute("studentHolidayList",studentHolidayList);
        return "studentHoliday";
    }
    /*
    审批假条
     */
    @RequestMapping("updateStudentHoliday")
    public String updateHoliday(int hid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        int i = headMasterService.updateStudentHoliday(hid, 2, master.getEname());
        return "redirect:studentHoliday";
    }

    /*
    个人请假
     */
    @RequestMapping("addEmployeeHoliday")
    public String addHoliday(){
        return "addEmployeeHoliday";
    }
    @RequestMapping("saveEmployeeHoliday")
    public String saveHoliday(Employee_Holiday employeeHoliday,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        employeeHoliday.setEmployee(master);
        headMasterService.addHeadMasterHoliday(employeeHoliday);
        return "successful";
    }

}
