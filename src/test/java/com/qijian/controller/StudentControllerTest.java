package com.qijian.controller;
import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author mahuahong
 * @Date 2023/3/17 00:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
//@WebMvcTest(StudentController.class)
public class StudentControllerTest {//注意：需是public

    @Resource
    private MockMvc mockMvc;

    //使用此注解注入的类，表明类中的所有方法都使用自定义返回的值，这样在测试的时候就不会真的去调用远程接口，而是返回一个我们预设的值
    @MockBean
    StudentService studentService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testGetById() throws Exception {
        Student build = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
//        Mockito.when(studentService.getById(Mockito.anyInt())).thenReturn(build);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getByNum?num=5005")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
