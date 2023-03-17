package com.qijian.mapper;
import com.qijian.po.Student;
import org.springframework.stereotype.Repository;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:56
 */

@Repository
public class StudentDao {

    //演示从数据库查询数据
    public Student getById(String id){

        return null;
    }

    //演示从数据库添加数据
    public String save(Student student){
        if(student != null){
            return "数据成功添加";
        }
        return "数据添加失败";
    }
}

