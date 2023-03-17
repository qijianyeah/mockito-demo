package com.qijian.service;

import com.qijian.po.Student;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:57
 */
public interface StudentService {

    public Student getById(String id);

    public String save(Student student);
}