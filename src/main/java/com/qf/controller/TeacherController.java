package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    //获取 当前对象
    @RequestMapping("")
    public String getTeacher(HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        request.setAttribute("employee",employee);
        return "";
    }
    //修改密码
    @RequestMapping("")
    public String updateUpwd(User user){
        int i = teacherService.updateUpwdByUname(user);
        if (i>0){
            return "";
        }
        return "";
    }
    //获取周报集合
    @RequestMapping("")
    public String getWeekReportList(HttpServletRequest request){
        List<WeekReport> weekReportList = teacherService.getWeekReportList();
        request.setAttribute("weekReportList",weekReportList);

        return "";
    }
    //获取周报
    @RequestMapping("")
    public String getWeekReport(int wid,HttpServletRequest request){
        WeekReport weekReport = teacherService.getWeekReport(wid);
        request.setAttribute("weekReport",weekReport);
        return "";
    }
    //周报打分，修改周报状态
    @RequestMapping("")
    public String updateWeekReport(WeekReport weekReport){
        int i = teacherService.updateWeekReport(weekReport);
        if (i>0){
            return "";
        }
        return "redirect: ?wid="+weekReport.getWid();
    }
    //获取待审核假条
    @RequestMapping("")
    public String getStudent_HolidayList(HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        List<Student_Holiday> student_holidayList = teacherService.getStudent_HolidayList(employee.getEname());
        request.setAttribute("student_holidayList",student_holidayList);
        return "";
    }
    //修改假条状态
    @RequestMapping("")
    public String updateStudent_Holiday(int hid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        teacherService.updateStudent_Holiday(hid,2,employee.getEname());
        return "";
    }
    //获取分数列表
    @RequestMapping("")
    public String getScoreList(HttpServletRequest request){
        List<Score> scoreList = teacherService.getScoreList();
        request.setAttribute("scoreList",scoreList);

        return "";
    }
    //录入学生分数
    @RequestMapping("add1")
    public String add1(){
        return "";
    }
    @RequestMapping("")
    public String addScore(Score score){
        int i = teacherService.addScore(score);
        if (i>0){
            return "";
        }
        return "";
    }
    //查看本班学生信息
    @RequestMapping("")
    public String getStudentList(int uid,HttpServletRequest request){
        Employee employee = teacherService.getTeacherByUid(uid);
        List<Student> studentList = teacherService.getStudentList(employee);

        request .setAttribute("studentList",studentList);
        return "";
    }
//  讲师发起请假
    @RequestMapping("add2")
    public String add2(){
        return "";
    }
    @RequestMapping("")
    public String addEmployeeHoliday(Employee_Holiday employee_holiday,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        employee_holiday.setEmployee(employee);
        int i = teacherService.addEmployeeHoliday(employee_holiday);
        if (i>0){
            return "";
        }
        return "";
    }
//成绩分析
//    @RequestMapping("")
//    public String getScoreBySid(int uid,HttpServletRequest request) {
//        Employee employee = teacherService.getTeacherByUid(uid);
//        List<Score> scores1 = teacherService.getScoreBySid(1, employee.getEname());
//        List<Score> scores2 = teacherService.getScoreBySid(2, employee.getEname());
//        List<Score> scores3 = teacherService.getScoreBySid(3, employee.getEname());
//        List<Score> scores4 = teacherService.getScoreBySid(4, employee.getEname());
//        request.setAttribute("scores1",scores1);
//        request.setAttribute("scores2",scores2);
//        request.setAttribute("scores3",scores3);
//        request.setAttribute("scores4",scores4);
//
//        return "";
//    }

    @RequestMapping("")
    public String getScoreBySid(int uid,HttpServletRequest request){
        Employee employee = teacherService.getTeacherByUid(uid);
        int score1 = teacherService.getAvgScore(1,employee.getEname());
        int score2 = teacherService.getAvgScore(2,employee.getEname());
        int score3 = teacherService.getAvgScore(3,employee.getEname());
        int score4 = teacherService.getAvgScore(4,employee.getEname());
        request.setAttribute("score1",score1);
        request.setAttribute("score2",score2);
        request.setAttribute("score3",score3);
        request.setAttribute("score4",score4);
        return "";
    }
    @RequestMapping("")
    public  String getScore(int sid,HttpServletRequest request){
        int score1 = teacherService.getScore(sid,1);
        int score2 = teacherService.getScore(sid,2);
        int score3 = teacherService.getScore(sid,3);
        int score4 = teacherService.getScore(sid,4);
        request.setAttribute("score1",score1);
        request.setAttribute("score2",score2);
        request.setAttribute("score3",score3);
        request.setAttribute("score4",score4);
        return "";
    }
}
