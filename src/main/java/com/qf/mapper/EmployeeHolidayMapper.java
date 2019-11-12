package com.qf.mapper;

import com.qf.pojo.Employee_Holiday;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:26
 */
public interface EmployeeHolidayMapper {
    //通过uid删除员工请假表的信息
    public int deleteEmployeeHolidayByUid(int uid);
    //班主任发起请假
    int addHoliday(Employee_Holiday employeeHoliday);
}
