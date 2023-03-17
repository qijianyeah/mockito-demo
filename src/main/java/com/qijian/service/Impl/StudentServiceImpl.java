package com.qijian.service.Impl;

import com.qijian.mapper.StudentDao;
import com.qijian.po.Student;
import com.qijian.po.User;
import com.qijian.service.StudentService;
import com.qijian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:57
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private UserService userService;

    @Override
    public Student getById(Integer id) {
        Student result = studentDao.getById(id);
        log.info("stu:{}",result);
        return result;
    }
    /**
     * 通过学号获取学生信息
     * @param num ID
     * @return
     */
    public Student getByNum(String num){
        return studentDao.getByNum(num);
    }

    @Override
    public String save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void doSomething(Integer stuId){
        Student student = this.getById(stuId);
        List<User> list = userService.list();
        for (User user:list) {
            if (user.getId().equals(student.getId())){
                //todo : do something
            }
        }
    }


    public int methodA(){
        //根据某成员变量的值去计算得出一个value，这个过程包含了复杂的逻辑和层层方法嵌套调用
        return 1;
    }


    public void methodB(){
        int key = methodA();
        switch(key){
            case 0:
                methodA();
                break;
            case 1:
                methodC();
                break;
            case 2:
                methodD();
                break;
            default:
                log.info("default");
        }
    }

    public void methodC(){
        System.out.println("methodC");
    }

    public void methodD() {
        System.out.println("methodD");

    }

    }
