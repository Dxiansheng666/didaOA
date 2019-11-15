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
    @RequestMapping("getStudnetInfo")//查看个人资料
    public String getStudentInfo(HttpSession session, HttpServletRequest request){
    User user = (User) session.getAttribute("user");
    Student student = studentService.getStudnetInfo(user.getUid());
    Classes classes = studentService.getClassesByUid(user.getUid());
    request.setAttribute("student",student);
    request.setAttribute("classes",classes);
        return "forms";//返回到页面
    }
    @RequestMapping("editStudentInfo")
    public String editStudentInfo(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        Student student=studentService.getStudnetInfo(user.getUid());
        request.setAttribute("student",student);
        System.out.println(user.getUid());
        System.out.println(student.getSname());
        return "update";
    }
    @RequestMapping("updateStudentInfo")
    public String updateStudentInfo(Student student,HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        student.setUser(user);
        int i =studentService.updateStudentInfo(student);
        if(i>0) {
            System.out.println(i);
            return "redirect:/getStudnetInfo";
        }
        return "updateStudentInfo";
    }
    @RequestMapping("updateUpwd")
    public String updateUpwd(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int i = studentService.updateUpwd(user.getUid());
        if(i>0){
            return "forms";
        }
        return "updateUpwd";
    }
    @RequestMapping("getWeekReportInfo")
    public String getWeekReportInfo(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int sid = studentService.getSidByUid(user.getUid());
        List<WeekReport> weekReportList= studentService.getWeekReportList(sid);
        for(WeekReport wk:weekReportList){
            System.out.println(wk);
        }
        request.setAttribute("weekReportList",weekReportList);
        return "getWeekReportInfo";
    }
    @RequestMapping("addWeekReport")
    public String addWeekReport(){
        return "forms";
    }
    @RequestMapping("addWRP")
    public String addWRP(){
        System.out.println(123);
        return "addWeekReport";
    }
    @RequestMapping("saveWeekReport")
    public String saveWeekReport(WeekReport weekReport,HttpSession session){
        User user = (User) session.getAttribute("user");
        int sid = studentService.getSidByUid(user.getUid());
        weekReport.setSid(sid);
        int i = studentService.addWeekReport(weekReport);
        if(i>0){
            return "redirect:getWeekReportInfo";
        }
        return "redirect:saveWeekReport";
    }
    @RequestMapping("deleteWeekReport")
    @ResponseBody
    public String deleteWeekReport(HttpSession session, HttpServletRequest request,int wid){
        User user = (User) session.getAttribute("user");
        int sid = studentService.getSidByUid(user.getUid());
        List<WeekReport> weekReportList = studentService.getWeekReportList(sid);
        for(WeekReport wrs :weekReportList) {
            System.out.println(wrs);
            if(wrs.getScore()!=null && wrs.getWid() == wid){
                return "不能删除！";
            }
        }
        int i = studentService.deleteWeekReport(wid);
        return "success";
    }
   @RequestMapping("addHoliday")
        public String add(){
        return "addHoliday";
        }

    @RequestMapping("saveHoliday")
    public String saveHoliday(Student_Holiday student_holiday,HttpSession session,HttpServletRequest request){
        Student student = new Student();
        User user1 = (User) session.getAttribute("user");
        System.out.println(user1);
        int sid = studentService.getSidByUid(user1.getUid());
        student.setUser(user1);
        student.setSname(user1.getUname());
        student_holiday.setStudent(student);
        System.out.println(student);
        studentService.addHoliday(student_holiday,session);
        Classes classes = studentService.getClassesByUid(user1.getUid());
        request.setAttribute("student",student);
        request.setAttribute("classes",classes);
        return "forms";
    }
    @RequestMapping("fanhui")
    public String fanhui(){
        return "redirect:getStudnetInfo";
    }
}
