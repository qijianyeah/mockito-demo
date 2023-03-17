package com.qijian.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qijian.conteoller.StudentController;
import com.qijian.po.Student;
import com.qijian.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/**
 * @author mahuahong
 * @Date 2023/3/17 00:02
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = {StudentController.class, StudentService.class})
@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    StudentController studentController;

    @MockBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }


    @Test
    public void getById() throws Exception {
        Student student = new Student();
        student.setId("001");
//        student.setName("张三");
        Mockito.when(studentService.getById("001"))
                .thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getById?id=001"))
                //需要携带的头信息，如token信息 header("Authorization","Bearer ..."))
//                .header("",""))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void save() throws Exception {
        Student student = new Student();
        student.setId("002");
//        student.setName("李四");
        Mockito.when(studentService.save(student))
                .thenReturn("数据成功添加");
        mockMvc.perform(MockMvcRequestBuilders.get("/student/save")
                        .contentType("application/json")
                        //需要携带的头信息，如token信息 header("Authorization","Bearer ..."))
//                .header("",""))
                        //将请求实体转换成String放入content里面
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
