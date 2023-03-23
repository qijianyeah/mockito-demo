package com.qijian.service.Impl;

import com.qijian.po.Student;
import com.qijian.service.StudentService;
import com.qijian.util.XxxUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * @author mahuahong
 * @Date 2023/3/21 11:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ StudentServiceImpl.class,XxxUtils.class })
public class StudentService2Test {

    @InjectMocks//spy方法会走真实的方法，而mock对象不会，spy()方法的参数是对象的实例，mock的参数是class
    private StudentService studentService = PowerMockito.spy(new StudentServiceImpl());


    @Test
    public void testMethodE1() throws Exception {
        PowerMockito.when(studentService,"privateMethod").thenReturn("private");
        String s = studentService.methodE(new Student());
        Assertions.assertEquals("privateMethod",s);
    }

    @Test
    public void testStaticMethod_withXxxMethod_returnFalse(){
//      为类的所有方法启用静态模拟。
        PowerMockito.mockStatic(XxxUtils.class);
        // mock掉对Redis的静态调用
        PowerMockito.when(XxxUtils.xxxMethod(true)).thenReturn(false);
        boolean b = StudentServiceImpl.staticMethod(true);
        Assertions.assertEquals(b,false);
    }

    @Test
    public void testStaticMethod_withXxxMethod_returnTrue(){
//      为类的所有方法启用静态模拟。
        PowerMockito.mockStatic(XxxUtils.class);
        // mock掉对Redis的静态调用
        PowerMockito.when(XxxUtils.xxxMethod(Mockito.anyBoolean())).thenReturn(true);
        boolean b = StudentServiceImpl.staticMethod(false);
        Assertions.assertEquals(b,true);
    }


}
