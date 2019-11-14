package com.qf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.*;
import com.qf.service.TeacherService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("teacher")
    public String getTeacher(HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        request.setAttribute("employee",employee);
        return "forms";
    }
    //修改密码
    @RequestMapping("UpdatePwd")
    public String updateUpwd(User user){

        int i = teacherService.updateUpwdByUname(user);
        if (i>0){
            return "index";
        }
        return "redirect:teacher";
    }
    //获取周报集合
    @RequestMapping("getWeekReport")
    public String getWeekReportList(@RequestParam(defaultValue = "1")int pageNum,HttpServletRequest request){
        PageHelper.startPage(pageNum,5);
        List<WeekReport> weekReportList = teacherService.getWeekReportList();
        PageInfo<WeekReport> pageInfo = new PageInfo<>(weekReportList);
        request.setAttribute("pageInfo",pageInfo);

        return "tables";
    }
    //获取周报
    @RequestMapping("addscore")
    public String getWeekReport(int wid,HttpServletRequest request){
        System.out.println(wid);
        WeekReport weekReport = teacherService.getWeekReport(wid);
        request.setAttribute("weekReport",weekReport);
        return "addscore";
    }
    //周报打分，修改周报状态
    @RequestMapping("updateweekreport")
    public String updateWeekReport(int score,int wid){
        System.out.println();
        int i = teacherService.updateWeekReport(score,2,wid);
        if (i>0){
            return "redirect:getWeekReport";
        }
        return "redirect:addscore?wid="+wid;
    }
    //获取待审核假条
    @RequestMapping("updateHoliday")
    public String getStudent_HolidayList(HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        List<Student_Holiday> student_holidayList = teacherService.getStudent_HolidayList(employee.getEname());
        request.setAttribute("student_holidayList",student_holidayList);
        return "";
    }
    //修改假条状态
    @RequestMapping("9")
    public String updateStudent_Holiday(int hid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        teacherService.updateStudent_Holiday(hid,2,employee.getEname());
        return "";
    }
    //获取分数列表
    @RequestMapping("11")
    public String getScoreList(HttpServletRequest request){
        List<Score> scoreList = teacherService.getScoreList();
        request.setAttribute("scoreList",scoreList);

        return "";
    }
    //录入学生分数
    @RequestMapping("addScore")
    public String add1(){
        return "insertscore";
    }
    @RequestMapping("savescore")
    public String addScore(Score score){
        System.out.println(score);
        int i = teacherService.addScore(score);
        if (i>0){
            return "index";
        }
        return "redirect:addScore";
    }
//    @RequestMapping("score")
//    public String det(){
//        return "echarts";
//    }
    //查看本班学生信息
    @RequestMapping("getStudent")
    public String getStudentList(int uid,HttpServletRequest request){
        Employee employee = teacherService.getTeacherByUid(uid);
        List<Student> studentList = teacherService.getStudentList(employee);

        request .setAttribute("studentList",studentList);
        return "";
    }
//  讲师发起请假
    @RequestMapping("add2")
    public String add2(){
        return "111111";
    }
    @RequestMapping("12")
    public String addEmployeeHoliday(Employee_Holiday employee_holiday,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        employee_holiday.setEmployee(employee);
        int i = teacherService.addEmployeeHoliday(employee_holiday);
        if (i>0){
            return "13";
        }
        return "14";
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

    @RequestMapping("score")
    public String op(){
        return "echarts";
    }
    @RequestMapping("score3")
    @ResponseBody
    public  Map<String,Object> getScoreBySid(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        List<String>  scoreList = teacherService.getAvgScore(employee.getEname());
        List<Double> list = new ArrayList<>();
        list.add(Double.parseDouble(scoreList.get(0)));
        list.add(Double.parseDouble(scoreList.get(1)));
        list.add(Double.parseDouble(scoreList.get(2)));
        list.add(Double.parseDouble(scoreList.get(3)));
        map.put("scoreList",list);
        for (Object s:scoreList
             ) {
            System.out.println(s);
        }
       // map.put("scoreList",scoreList);

        return map;
    }
    @RequestMapping("getScore")
    public  String getScore(int sid, Model model){
       List<Integer> scores = teacherService.getScore(sid);
        for (int score:scores
             ) {
            System.out.println(score);
        }
        model.addAttribute("scores",scores);
        return "echarts1";
    }
}
