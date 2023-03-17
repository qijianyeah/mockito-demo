package com.qijian.service.Impl;

import com.alibaba.fastjson.JSON;
import com.qijian.conteoller.StudentController;
import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author caijiapeng
 * @date 2023-03-17 16:07
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentController2Test {

    @InjectMocks
    private StudentController studentController = new StudentController();

    @Mock
    private StudentService fakeStudentService;

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

    private static Student student;

    @BeforeClass
    public static void init(){
        student = Student.builder()
                .id(1)
                .num("001")
                .classNum(4)
                .dormitoryNum(202)
                .userId(1)
                .build();
    }

    @Test
    public void testGetByNum() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
//        Mockito.when(fakeStudentService.getById(fakeStudent.getId())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getByNum?num=002")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
//        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getById?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
//        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")
                        .content(JSON.toJSONString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
