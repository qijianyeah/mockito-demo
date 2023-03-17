package com.qijian.service.Impl;
import com.qijian.mapper.StudentDao;
import com.qijian.po.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * @author mahuahong
 * @Date 2023/3/16 23:59
 */
class StudentServiceTest {

    //创建一个实例，简单的说是这个Mock可以调用真实代码的方法，
    // 其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
    @InjectMocks
    StudentServiceImpl studentService;

//    @Spy：对函数的调用均执行真正部分。
    //对函数的调用均执行mock（即虚假函数），不执行真正部分。
    @Mock
    StudentDao studentDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {
        Student student = new Student();
        student.setId("001");
//        student.setName("张三");
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentDao.getById("001"))
                .thenReturn(student);
        //assertThat后面跟着断言的判断语句
//        assertThat(studentService.getById(student.getId()).getName())
//                .isEqualTo("张三");
//        Assertions.assertEquals(studentService.getById(student.getId()).getName(),"张三");

    }

    @Test
    public void save() {
        Student student = new Student();
        student.setId("002");
//        student.setName("李四");
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentDao.save(student))
                .thenReturn("数据成功添加");
        //assertThat后面跟着断言的判断语句
//        assertThat(studentService.save(student))
//                .isEqualTo("数据成功添加");
        Assertions.assertEquals(studentService.save(student),"数据成功添加");

    }
}