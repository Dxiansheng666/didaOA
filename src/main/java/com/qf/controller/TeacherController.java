package com.qf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.*;
import com.qf.service.HeadMasterService;
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
    @Autowired
    private HeadMasterService headMasterService;

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
    public String getStudent_HolidayList(@RequestParam(defaultValue = "1")int pageNum,HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum,8);
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        List<Student_Holiday> student_holidayList = teacherService.getStudent_HolidayList(employee.getEname());
        PageInfo<Student_Holiday> pageInfo = new PageInfo<Student_Holiday>(student_holidayList);
        request.setAttribute("pageInfo",pageInfo);
        return "shenpiholiday";
    }
    //修改假条状态
    @RequestMapping("updateHoliday1")
    public String updateStudent_Holiday(int hid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        teacherService.updateStudent_Holiday(hid,2,employee.getEname());
        return "redirect:updatetHoliday";
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

//  讲师发起请假
    @RequestMapping("add2")
    public String add2(HttpServletRequest request){

        List<Employee> boss = headMasterService.getBossByRoleName("校长");
        request.setAttribute("list",boss);
        return "addHoliday";
    }
    @RequestMapping("save")
    public String addEmployeeHoliday(Employee_Holiday employee_holiday,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee employee = teacherService.getTeacherByUid(user.getUid());
        employee_holiday.setEmployee(employee);
        int i = teacherService.addEmployeeHoliday(employee_holiday);
        return "redirect:add2";

    }
//成绩分析

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


        return map;
    }
    @RequestMapping("score2")
    public String opc(int sid,Model model){
        model.addAttribute("sid", sid);
        return "echarts1";
    }
    @RequestMapping("getScore")
    @ResponseBody
    public  Map<String,Object> getScore(int sid){
       List<Integer> scores = teacherService.getScore(sid);
        for (int score:scores
             ) {
            System.out.println(score);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("scores",scores);
        return map;
    }
}
