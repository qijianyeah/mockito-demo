package com.qijian.service.Impl;

import com.qijian.mapper.StudentDao;
import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:57
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getById(String id) {
        return studentDao.getById(id);
    }

    @Override
    public String save(Student student) {
        return studentDao.save(student);
    }
}
