package com.qijian.service;

import com.qijian.po.Student;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:57
 */
public interface StudentService {

    /**
     * 通过ID获取学生信息
     * @param id ID
     * @return
     */
    public Student getById(Integer id);

    /**
     * 通过学号获取学生信息
     * @param num num
     * @return
     */
    public Student getByNum(String num);
    /**
     * 保存
     * @param student
     * @return
     */
    public String save(Student student);

    /**
     * do something
     */
    public void doSomething(Integer StuId);

    public int methodA();

    public void methodB();

    public void methodC();

    public void methodD();
}