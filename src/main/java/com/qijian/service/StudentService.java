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

    /**
     * method of a
     * @return
     */
    public int methodA();

    /**
     * method of b
     */
    public void methodB();

    /**
     * method of c
     */
    public void methodC();

    /**
     * method of d
     */
    public void methodD();

    /**
     * invoking private method .
     * method of e
     */
    public String methodE(Student student);


}