package com.qijian.mapper;
import com.qijian.po.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:56
 */
@Slf4j
@Repository
public class StudentDao {

    //演示从数据库查询数据
    public Student getById(Integer id){
        if (id == 1){
            log.info("id = 1");
            return Student.builder()
                    .id(1)
                    .num("3306")
                    .dormitoryNum(505)
                    .classNum(1)
                    .userId(1).build();
        }

        return null;
    }

    //演示从数据库添加数据
    public String save(Student student){
        if(student != null){
            return "数据成功添加";
        }
        return "数据添加失败";
    }

    //查询数据
    public Student getByNum(String num) {
        Student student = new Student();
        if ("5005".equals(num)){
            student.setNum("5005");
            student.setClassNum(01);
            student.setId(001);
            student.setUserId(002);
        }else {
            return student;
        }
        return student;
    }
}

