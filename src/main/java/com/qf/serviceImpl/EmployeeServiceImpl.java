package com.qf.serviceImpl;

import com.qf.mapper.EmployeeMapper;
import com.qf.pojo.Employee;
import com.qf.pojo.Employee_Holiday;
import com.qf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/12
 * @Time 11:38
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public int deleteEmployeeByUid(int uid) {
        return employeeMapper.deleteEmployeeByUid(uid);
    }

    @Override
    public Employee getHeadMasterByUid(int uid) {
        return employeeMapper.getHeadMasterByUid(uid);
    }

    @Override
    public List<Employee> getEmployList() {
        return employeeMapper.getEmployList();
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeByUid(int uid) {
        return employeeMapper.getHeadMasterByUid(uid);
    }

    @Override
    public Employee getEmpByEname(String ename) {
        return employeeMapper.getEmpByEname(ename);
    }

    @Override
    public List<Employee_Holiday> getEmpholidayByUid(int uid) {
        return employeeMapper.getEmpholidayByUid(uid);
    }

}
