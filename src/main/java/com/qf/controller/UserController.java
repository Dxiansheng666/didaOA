package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.*;
import com.qf.util.MD5Utils;
import com.qf.util.Pinyinutils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    private CourseService courseService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClassesService classesService;
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
     * 超级管理员对课程进行操作
     */
    //增加
    @RequestMapping("addCourse")
    public String addCourse(String course_name){
        int i = courseService.addCourse(course_name);
        return "success";
    }
    //减少
    @RequestMapping("deleteCourse")
    public String deleteCourse(int course_id){
        courseService.deleteCourse(course_id);
        return "success";
    }
    //修改
    @RequestMapping("updateCourse")
    public String updateCourse(Course course){
        courseService.updateCourse(course);
        return "success";
    }
    //查找全部课程
    @RequestMapping("getCourseList")
    public String getCourseList(Model model){
        List<Course> courseList = courseService.getCourseList();
        model.addAttribute("courseList",courseList);
        //发送到一个显示页面，暂定
        return "";
    }
    @RequestMapping("getCourseById")
    public String getCourseById(int course_id,Model model){
        Course course = courseService.getCourseById(course_id);
        model.addAttribute("course",course);
        //发送到一个显示页面，暂定
        return "";
    }

    /**
     * 管理员对角色进行管理
     */
    //增加
    @RequestMapping("addRole")
    public String addRole(String rolename){
        roleService.addRole(rolename);
        //发送到一个显示页面，暂定
        return "";
    }
    //删
    @RequestMapping("deleteRole")
    public String deleteRole(int roleid){
        roleService.deleteRole(roleid);
        //发送到一个显示页面，暂定
        return "";
    }
    //修改
    @RequestMapping("updateRole")
    public String updateRole(Role role){
        roleService.updateRole(role.getRolename(),role.getRoleid());
        //发送到一个显示页面，暂定
        return "";
    }
    //查看全部角色
    @RequestMapping("rolenameList")
    public String getRoleList(Model model){
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList",roleList);
        //发送到一个显示页面，暂定
        return "";
    }
    //结合修改
    @RequestMapping("getRoleById")
    public String getRoleById(int roleid,Model model){
        Role role = roleService.getRolenameById(roleid);
        model.addAttribute("role",role);
        //发送到一个显示页面，暂定
        return "";
    }


    /**
     * 超级管理员对用户表添加学生
     */
    @RequestMapping("addStudent")
    public String addStudent(String uname){
        String pinyin = Pinyinutils.getPingYin(uname);
        String upwd = MD5Utils.md5("123456");
        userService.addUser(pinyin,upwd,"学生");
        //发送到一个显示页面，暂定
        return "";
    }
    /**
     * 超级管理员对用户表添加其他角色
     * 添加员工时也将信息添加进user表
     */
    @RequestMapping("addEmployee")
    public String addUser(Employee employee,String rolename){
        String pinyin = Pinyinutils.getPingYin(employee.getEname());
        String upwd = MD5Utils.md5("123456");
        int uid = userService.addUser(pinyin, upwd, rolename);
        employee.setUid(uid);
        employeeService.addEmployee(employee);
        //发送到一个显示页面，暂定
        return "";
    }
    //超级管理员删除用户
    @RequestMapping("deleteUser")
    public String deleteUser(int uid){
        int i =userService.deleteUser(uid);
        if (i==2){
            //删除员工成功
            return "";
        }else if (i==1){
            //删除学生成功
            return "";
        }else {
            //删除均失败
            return "";
        }
    }
    //超级管理员重置用户密码
    @RequestMapping("resetUpwd")
    public String resetUpwd(int uid){
        userService.updatePasswordByAdmin(uid);
        //发送到一个显示页面，暂定
        return "";
    }
    //超级管理员搜索用户
    @RequestMapping("seekUser")
    public String seekUser(int uid, String uname, String rolename){
        userService.getLikeUser(uid, uname, rolename);
        //发送到一个显示页面，暂定
        return "";
    }
    /**
     * 管理员对员工信息进行管理包含搜索
     */
    //增加员工即增加用户 上有

    //删除员工即删除用户 上有
    //查询所有员工
    @RequestMapping("getEmployeeAll")
    public String getEmployeeAll(Model model){
        List<Employee> employList = employeeService.getEmployList();
        model.addAttribute("employList",employList);
        //发送到一个显示页面，暂定
        return "";
    }
    //查看通过uid 得到某个员工的信息
    @RequestMapping("getEmpByUid")
    public String getEmployeeByUid(int uid,Model model){
        Employee employeeByUid = employeeService.getEmployeeByUid(uid);
        model.addAttribute("employee",employeeByUid);
        //发送到一个显示页面，暂定
        return "";
    }
    //搜索员工
    @RequestMapping("getEmpByEname")
    public String getEmpByEname(String ename,Model model){
        if (ename.equals(null)||ename.contains("")){
            return "redirect:getEmployeeAll";
        }else {
            Employee empByEname = employeeService.getEmpByEname(ename);
            model.addAttribute("empByEname",empByEname);
            //发送到一个显示页面，暂定
            return "";
        }
    }
    /**
     * 管理员对班级信息管理
     */
    //将老师和班主任列表发送到页面
    @RequestMapping("addClassesPage")
    public String addClassesPage(Model model){
        List<String> tea = userService.getTea("老师");
        List<String> tea2 = userService.getTea("班主任");
        model.addAttribute("tea",tea);
        model.addAttribute("tea2",tea2);
        //发送到一个显示页面，暂定
        return "";
    }
    //增加班级
    @RequestMapping("addClasses")
    public String addClasses(Classes classes){
        classesService.addClasses(classes);
        //发送到一个显示页面，暂定
        return "";

    }
    //班级列表
    @RequestMapping("getClassesList")
    public String getClassesList(Model model){
        List<Classes> classesList = classesService.classesList();
        model.addAttribute("classesList",classesList);
        //发送到一个显示页面，暂定
        return "";
    }
    //删除班级
    @RequestMapping("deleteClasses")
    public String deleteClasses(int class_id){
        classesService.deleteClasses(class_id);
        //发送到一个显示页面，暂定
        return "";
    }
    //修改班级先得到该班级信息
    @RequestMapping("getClassesById")
    public String getClassesById(int class_id,Model model){
        Classes classes = classesService.getClassesById(class_id);
        model.addAttribute("classes",classes);
        List<String> tea = userService.getTea("老师");
        List<String> tea2 = userService.getTea("班主任");
        model.addAttribute("tea",tea);
        model.addAttribute("tea2",tea2);
        //发送到一个显示页面，暂定
        return "";
    }
    //修改班级信息
    @RequestMapping("updateClasses")
    public String updateClasses(Classes classes){
        classesService.updateClasses(classes);
        //发送到一个显示页面，暂定
        return "";
    }

}
