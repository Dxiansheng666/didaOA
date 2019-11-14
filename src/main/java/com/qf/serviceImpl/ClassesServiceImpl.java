package com.qf.serviceImpl;

import com.qf.mapper.ClassesMapper;
import com.qf.pojo.Classes;
import com.qf.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/12
 * @Time 11:26
 */
@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public int addClasses(Classes classes) {
        return classesMapper.addClasses(classes);
    }

    @Override
    public int deleteClasses(int class_id) {
        return classesMapper.deleteClasses(class_id);
    }

    @Override
    public Classes getClassesById(int class_id) {
        return classesMapper.getClassesById(class_id);
    }

    @Override
    public int updateClasses(Classes classes) {
        return classesMapper.updateClasses(classes);
    }

    @Override
    public List<Classes> classesList() {
        return classesMapper.classesList();
    }

    @Override
    public List<Classes> getClassesListByEname(String ename) {
        return classesMapper.getClassesListByEname(ename);
    }

    @Override
    public List<Classes> getClassesListByTname(String ename) {
        return classesMapper.getClassesListByTname(ename);
    }
}
