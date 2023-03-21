package com.qijian.conteoller;

import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mahuahong
 * @date 2023/3/16 23:58
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/getById")//http://127.0.0.1:8100/student/getById?id=1
    public Student getById(@RequestParam("id") Integer id){
        return studentService.getById(id);
    }

    @GetMapping("/getByNum")//http://127.0.0.1:8100/student/getByNum?num=5005
    public Student getByNum(@RequestParam("num") String num){
        return studentService.getByNum(num);
    }

    @PostMapping("/save")
    public String save(@RequestBody Student student){
        return studentService.save(student);
    }
}

