package com.qijian.controller;

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
//@RunWith(MockitoJUnitRunner.class) //报错：Unnecessary stubbings detected in test class: StudentControllerTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController = new StudentController();

    @Mock
    private StudentService fakeStudentService;

    // 初始化MockMvc对象
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


    /*
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */


    /*方法:GET
    URL:http://127.0.0.1:8100/student/getByNum?num=5005
    */
    @Test
    public void testGetByNum() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getById(fakeStudent.getId())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getByNum?num=002")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /*方法:GET
    URL:http://127.0.0.1:8100/student/getById?id=1
    */
    @Test
    public void testGetById() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/getById?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /*方法:POST
    URL:http://127.0.0.1:8100/student/save
    */
    @Test
    public void testSave() throws Exception {
        Student fakeStudent = Student.builder().id(1).num("002").classNum(4).dormitoryNum(202).userId(1).build();
        Mockito.when(fakeStudentService.getByNum(fakeStudent.getNum())).thenReturn(fakeStudent);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")
                        .content(JSON.toJSONString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
