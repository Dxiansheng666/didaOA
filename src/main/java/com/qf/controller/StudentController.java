package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("getStudnetInfo")
    public String getStudentInfo(HttpSession session, HttpServletRequest request){
    User user = (User) session.getAttribute("user");
    Student student = studentService.getStudnetInfo(user.getUid());
    request.setAttribute("student",student);
    return "getstudent";
    }
    @RequestMapping("updateUpwd")
    public String updateUpwd(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int i = studentService.updateUpwd(user.getUid());
        if(i>0){
            return "index";
        }
        return "updateUpwd";
    }
    @RequestMapping("getWeekreportInfo")
    public String getWeekReportInfo(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        List<WeekReport> weekReportList= studentService.getWeekReportList(user.getUid());
        for(WeekReport wk:weekReportList){
            System.out.println(wk);
        }
        request.setAttribute("weekReportList",weekReportList);
        return "weekReportList";
    }
    @RequestMapping("addWeekReport")
    public String addWeekReport(){
        return "weekReportList";
    }
    @RequestMapping("saveWeekReport")
    public String saveWeekReport(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int i = studentService.addWeekReport(user.getUid());
        if(i>0){
            return "weekReportList";
        }
        return "addweekReport";
    }
    @RequestMapping("deleteWeekReport")
    @ResponseBody
    public String deleteWeekReport(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        List<WeekReport> weekReportScoreList = studentService.getWeekReportScoreList(user.getUid());
        for(WeekReport wrs :weekReportScoreList) {
            System.out.println(wrs);
            if(wrs!=null){
                return "删除失败！";
            }
        int i = studentService.deleteWeekReport(user.getUid());

        }
        return "weekReportList";
    }
   @RequestMapping("add")
        public String add(){
        return "holiday";
        }

    @RequestMapping("saveHoliday")
    public String saveHoliday(Student_Holiday student_holiday,HttpSession session){
        Student student = new Student();
        User user1 = (User) session.getAttribute("user1");
        int sid = studentService.getSidByUid(user1.getUid());
        student.setSid(sid);
        student.setSname(user1.getUname());
        student_holiday.setStudent(student);
        studentService.addHoliday(student_holiday,session);
        return "redirect:index";
    }
}
