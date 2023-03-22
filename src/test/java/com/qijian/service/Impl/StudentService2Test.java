package com.qijian.service.Impl;

import com.qijian.po.Student;
import com.qijian.service.StudentService;
import com.qijian.util.XxxUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.AdditionalMatchers.eq;


/**
 * @author mahuahong
 * @Date 2023/3/21 11:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ StudentServiceImpl.class,XxxUtils.class })
public class StudentService2Test {

    @InjectMocks
    private StudentService studentService = PowerMockito.spy(new StudentServiceImpl());


    @Test
    public void testMethodE1() throws Exception {
        PowerMockito.when(studentService,"privateMethod").thenReturn("private");
        String s = studentService.methodE(new Student());
        Assertions.assertEquals("privateMethod",s);
    }

    @Test
    public void testStaticMethod(){
//        模拟静态方法调用
        PowerMockito.mockStatic(XxxUtils.class);
        // mock掉对Redis的静态调用
        PowerMockito.when(XxxUtils.xxxMethod(false)).thenReturn(false);
        boolean b = StudentServiceImpl.staticMethod(false);
        Assertions.assertEquals(b,false);
    }



}
