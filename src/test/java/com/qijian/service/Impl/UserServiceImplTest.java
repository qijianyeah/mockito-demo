package com.qijian.service.Impl;

import com.qijian.base.ResponseData;
import com.qijian.mapper.UserMapper;
import com.qijian.po.User;
import com.qijian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test; //错误
import org.junit.Test;//注意：
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mahuahong
 * @Date 2023/3/16 22:49
 */
@Slf4j
public class UserServiceImplTest {

    private static final UserService testService = new UserServiceImpl();
    private static final UserMapper userMapper = Mockito.mock(UserMapper.class);

    //针对所有测试，只执行一次，且必须为static void  运行junit测试类时第一个被执行的方法
    //被用作执行计算代价很大的任务，如打开数据库连接。
    @BeforeClass
    public static void init() {
        ReflectionTestUtils.setField(testService, "userMapper", userMapper);
    }

    //针对所有测试，只执行一次，且必须为static void  运行junit测试类是最后一个被执行的方法
    //该类型的方法被用作执行类似关闭数据库连接的任务。
    @AfterClass
    public static void afterClass(){
    }

//    单元测试用例执行顺序
//    @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
//    其他注解说明补充：
//        a. @Before注解 : junit测试类中的任意一个测试方法执行 前 都会执行此方法,该类型的方法可以被用来为测试方法初始化所需的资源。
//        b. @After注解  : junit测试类中的任意一个测试方法执行后 都会执行此方法, 即使被@Test 或 @Before修饰的测试方法抛出异常.该类型的方法被用来关闭由@Before注解修饰的测试方法打开的资源。
//        c. @Test 注解  : 测试方法包含了真正的测试代码，并且会被Junit应用为要测试的方法。可选的参数：expected 表示此测试方法执行后应该抛出的异常，（值是异常名）;timeout 检测测试方法的执行时间

    /**
     * 案例：模拟没有传递参数的情况
     */
    @Test
    public void testListForLessThan_UserIsNull(){
//        User user = User.builder().id(1).age(18).name("qijian").build();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
        Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
        ResponseData<List<User>> responseData = testService.listForLessThan(null);
        Assertions.assertThat(responseData.getCode()).isEqualTo(500);
    }


    /**
     * 案例：模拟没有传递年龄字段的值
     */
    @Test
    public void testListForLessThan_AgeIsNull(){
//        User user = User.builder().id(1).age(18).name("qijian").build();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
        Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
        User user1 = User.builder().name("qijian").build();
        ResponseData<List<User>> responseData = testService.listForLessThan(user1);
        Assertions.assertThat(responseData.getCode()).isEqualTo(500);
    }

    /**
     * 案例：模拟传入的年龄的值小于等于0 或大于 120的情况
     */
    @Test
    public void testListForLessThan_AgeIsIllegal(){
//        User user = User.builder().id(1).age(18).name("qijian").build();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
        Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
        User user1 = User.builder().name("qijian").age(121).build();
        ResponseData<List<User>> responseData = testService.listForLessThan(user1);
        Assertions.assertThat(responseData.getCode()).isEqualTo(500);
    }

    /**
     * 成功案例
     */
    @Test
    public void testListForLessThan_Ok(){
        //User user = User.builder().id(1).age(18).name("qijian").build();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);

        User user0 = User.builder().id(0).name("qijian").age(18).gender(1).build();

        //如果入参是自定义的类对象，则需要利用Mockito.any()来进行，也可以自己new出来一个新类来进行：
        Mockito.when(userMapper.listForLessThan(Mockito.anyInt())).thenReturn(new ArrayList<>());
        User user1 = User.builder().name("qijian").age(18).build();
        ResponseData<List<User>> responseData = testService.listForLessThan(user0);
        Assertions.assertThat(responseData.getCode()).isEqualTo(200);
    }

}