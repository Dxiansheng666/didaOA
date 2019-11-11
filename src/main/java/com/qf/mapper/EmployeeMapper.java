package com.qf.mapper;
import com.qf.pojo.Employee;
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


}
