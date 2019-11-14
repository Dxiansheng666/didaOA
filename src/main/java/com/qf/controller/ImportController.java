package com.qf.controller;

import com.qf.pojo.Classes;
import com.qf.pojo.Student;
import com.qf.service.FileupdownService;
import com.qf.service.HeadMasterService;
import com.qf.service.StudentService;
import com.qf.service.UserService;
import com.qf.util.ExcelUtils;
import com.qf.util.MD5Utils;
import com.qf.util.Pinyinutils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ImportController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private HeadMasterService headMasterService;
    /*
    Excel表格导入
     */
    @Autowired
    private FileupdownService fileupdownService;
    @RequestMapping("upload")
    @ResponseBody
    public String uploadExcel(int class_id,HttpServletRequest request) throws Exception {
        System.out.println(class_id);
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("filename");
        if(file.isEmpty()){
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = fileupdownService.getBankListByExcel(inputStream, file.getOriginalFilename(),class_id);
        inputStream.close();

        for (int i = 0; i<list.size(); i++){
            Student student = (Student) list.get(i);
            String pingYin = Pinyinutils.getPingYin(student.getSname());
            if(userService.getUserByUname(pingYin)==null) {
                userService.addUser(pingYin, MD5Utils.md5("123456"), "学生");
            }
            student.setUser(userService.getUserByUname(pingYin));
            Classes classes = new Classes();
            classes.setClass_id(class_id);
            student.setClasses(classes);
            int i1 = studentService.addStudent(student);
            System.out.println(student);
        }
        return "redirect:getStudentList";

    }
    @RequestMapping("downstudent")
    public String down(int class_id,HttpServletResponse response){
        //获取学生数据
        List<Student> studentList = headMasterService.getStudentListByClassId(class_id);

        //excel标题
        String[] title = {"姓名","性别","年龄","手机号","邮箱"};

        //文件名
        String filename = "学生信息表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "学生信息表";
        String[][] content = new String[studentList.size()][5];
        for (int i=0;i<studentList.size();i++){
            content[i] = new String[title.length];
            Student student =studentList.get(i);
            content[i][0] =student.getSname();
            content[i][1] =student.getSsex();
            content[i][2] =student.getSage();
            content[i][3] =Integer.toString(student.getSphone());
            content[i][4] =student.getSemail();
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response,filename);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "getStudentList";
    }
    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
