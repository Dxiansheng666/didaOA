package com.qf.service;

import com.qf.pojo.Classes;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/12
 * @Time 11:21
 */

public interface ClassesService {
    //添加班级及讲师和班主任
    public int addClasses(Classes classes);
    //删除班级信息
    public int deleteClasses(int class_id);
    //通过id查询班级信息
    public Classes getClassesById(int class_id);
    //修改班级信息
    public int updateClasses(Classes classes);
    //查询所有班级信息
    public List<Classes> classesList();

}
