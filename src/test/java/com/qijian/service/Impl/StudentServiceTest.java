package com.qijian.service.Impl;
import com.qijian.mapper.StudentMapper;
import com.qijian.po.Student;
//import org.junit.jupiter.api.Assertions;
import com.qijian.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


/**
 * @author mahuahong
 * @Date 2023/3/16 23:59
 */
@Slf4j
class StudentServiceTest {

    //创建一个实例，简单的说是这个Mock可以调用真实代码的方法，
    // 其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
    @InjectMocks
    StudentServiceImpl studentService;

//    @Spy：对函数的调用均执行真正部分。
    //对函数的调用均执行mock（即虚假函数），不执行真正部分。
    @Mock
StudentMapper studentMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    //不用理会方法中调用的函数结果，mock打桩后可以指定结果返回
    @Test
    public void testGetById() {
        Student student = new Student();
        student.setId(001);
        student.setClassNum(01);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentMapper.getById(001))
                .thenReturn(student);
        Assertions.assertThat(studentService.getById(student.getId()).getClassNum()).isEqualTo(01);
    }


    //无效单元测试
    @Test
    public void testSave() {
        Student student = new Student();
        student.setId(002);
        student.setDormitoryNum(505);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentMapper.save(student))
                .thenReturn("数据成功添加");
        Assertions.assertThat(studentService.save(student)).isEqualTo("数据成功添加");
    }


    //this.getById(stuId); 或 getById(stuId); 调用的是当前待测试类的另一个方法
    @Test
    public void testDoSomething(){
        StudentService spy = spy(studentService);
    }

    //调用的是当前待测试类的另一个方法(对真实对象打桩，就要用到spy) 使用spy()
    @Test
    public void testMethodB(){
//        StudentService t = new StudentServiceImpl();
//        StudentService spyT = spy(t);
        StudentService spyT = spy(studentService);
        //第一次，第二次，第三次调用methodA时，分别返回0，1，2
        Mockito.when(spyT.methodA()).thenReturn(0,1);
        for(int i=0; i<=1; i++){
            spyT.methodB();
        }
        //验证方法是否被调用
        verify(spyT).methodC();
        log.info("ertyui");
        //assert && verify
    }






}