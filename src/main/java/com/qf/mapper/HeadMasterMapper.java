package com.qf.mapper;

import com.qf.pojo.Employee;

public interface HeadMasterMapper {
    /*
    查看个人资料
     */
    public Employee getHeadMasterByUid(int uid);

    /**
     *修改密码
     */
}
