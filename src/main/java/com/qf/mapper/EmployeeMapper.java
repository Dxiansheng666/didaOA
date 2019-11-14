package com.qf.mapper;
import com.qf.pojo.Employee;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:22
 */
public interface EmployeeMapper {
    //如果是员工的话通过uid删除员工信息
    public int deleteEmployeeByUid(int uid);
    /*
    查看个人资料
     */
    public Employee getHeadMasterByUid(int uid);

    //查看员工列表
    public List<Employee> getEmployList();
    //增加员工
    public int addEmployee(Employee employee);
    //修改员工信息
    public int updateEmployee(Employee employee);
    //查询某个员工的具体信息
    public Employee getEmployeeByUid(int uid);
    //搜索员工，用姓名
    public Employee getEmpByEname(String ename);
    //获取校长集合
    List<Employee> getBossByRoleName(String boss);
}
