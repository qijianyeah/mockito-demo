package com.qijian.service.Impl;
import com.qijian.mapper.StudentDao;
import com.qijian.po.Student;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
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

    //不用理会方法中调用的函数结果，mock打桩后可以指定结果返回
    @Test
    public void getById() {
        Student student = new Student();
        student.setId("001");
        student.setClassNum(01);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentDao.getById("001"))
                .thenReturn(student);
        Assertions.assertThat(studentService.getById(student.getId()).getClassNum()).isEqualTo(01);
    }

    @Test
    public void save() {
        Student student = new Student();
        student.setId("002");
        student.setDormitoryNum(505);
        //when里面带的是条件，thenReturn里面表示的是返回结果
        Mockito.when(studentDao.save(student))
                .thenReturn("数据成功添加");
        Assertions.assertThat(studentService.save(student)).isEqualTo("数据成功添加");

    }
}