package com.qf.mapper;

import com.qf.pojo.Classes;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/12
 * @Time 9:52
 * 管理员对班级进行管理
 */
public interface ClassesMapper {
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
    //根据ename查询班级
    List<Classes> getClassesListByEname(String ename);

    List<Classes> getClassesListByTname(String ename);
}
