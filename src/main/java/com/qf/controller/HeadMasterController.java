package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.mapper.EmployeeHolidayMapper;
import com.qf.mapper.EmployeeMapper;
import com.qf.pojo.*;
import com.qf.service.ClassesService;
import com.qf.service.HeadMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HeadMasterController {
    @Autowired
    private HeadMasterService headMasterService;
    @Autowired
    private EmployeeMapper employeeMapper;
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
    public String getweekreport(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "0")int class_id, HttpSession session,HttpServletRequest request){
        PageHelper.startPage(pageNum,5);
        User user = (User) session.getAttribute("user");
        Employee headMasterByUid = headMasterService.getHeadMasterByUid(user.getUid());
        List<Classes> classesList = classesService.getClassesListByEname(headMasterByUid.getEname());
        request.setAttribute("list",classesList);
        List<WeekReport> weekReportList = headMasterService.getWeekReportListByUid(user.getUid(),class_id);
        PageInfo<WeekReport> pageInfo = new PageInfo<WeekReport>(weekReportList);
        request.setAttribute("class_id",class_id);
        request.setAttribute("pageInfo",pageInfo);
        return "HMtable";
    }
    /*
    获取待审批假条
     */
    @RequestMapping("updateStudentHoliday")
    public String shenpi(@RequestParam(defaultValue = "1")int pageNum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum,8);
        List<Student_Holiday> studentHolidayList = headMasterService.getStudentHolidayByUid(user.getUid());
        PageInfo<Student_Holiday> pageInfo = new PageInfo<Student_Holiday>(studentHolidayList);
        model.addAttribute("pageInfo",pageInfo);
        return "shenpiholiday";
    }
    /*
    审批假条
     */
    @RequestMapping("updateStudentHoliday1")
    public String updateHoliday(int hid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        int i = headMasterService.updateStudentHoliday(hid, 2, master.getEname());
        return "redirect:updateStudentHoliday";
    }

    /*
    个人请假
     */
    @RequestMapping("addEmployeeHoliday")
    public String addHoliday(HttpServletRequest request){
        List<Employee> boss = headMasterService.getBossByRoleName("校长");
        request.setAttribute("list",boss);
        return "addHoliday";
    }
    @RequestMapping("saveEmployeeHoliday")
    public String saveHoliday(Employee_Holiday employeeHoliday,HttpSession session){
        User user = (User) session.getAttribute("user");
        Employee master = headMasterService.getHeadMasterByUid(user.getUid());
        employeeHoliday.setEmployee(master);
        headMasterService.addHeadMasterHoliday(employeeHoliday);
        return "redirect:addEmployeeHoliday";
    }

    /*
    跳转上传excel表格页面
     */
    @Autowired
    private ClassesService classesService;
    @RequestMapping("getStudentList")
    public String get(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        Employee headMasterByUid = headMasterService.getHeadMasterByUid(user.getUid());
        System.out.println(headMasterByUid);
        List<Classes> classesList=null;
        if(user.getRolename()=="班主任"){
            classesList = classesService.getClassesListByEname(headMasterByUid.getEname());
        }else {
            classesList = classesService.getClassesListByTname(headMasterByUid.getEname());
        }
        request.setAttribute("list",classesList);
        return "upload";
    }
    /**
     * 获取个人请假信息
     */
    @RequestMapping("getempholiday")
    public String getempholiday(@RequestParam(defaultValue = "1")int pageNum,HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum,8);
        List<Employee_Holiday> employeeHolidayList = employeeMapper.getEmpholidayByUid(user.getUid());
        PageInfo<Employee_Holiday> pageInfo = new PageInfo<>(employeeHolidayList);
        request.setAttribute("pageInfo",pageInfo);
        return "empholiday";
    }
}
