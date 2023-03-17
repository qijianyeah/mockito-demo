package com.qijian.conteoller;

import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:58
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getById")
    public Student getById(@RequestParam("id") String id){
        return studentService.getById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Student student){
        return studentService.save(student);
    }
}

