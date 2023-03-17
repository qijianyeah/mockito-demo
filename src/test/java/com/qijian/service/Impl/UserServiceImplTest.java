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

    //针对所有测试，只执行一次，且必须为static void
    @BeforeClass
    public static void init() {
        ReflectionTestUtils.setField(testService, "userMapper", userMapper);
    }

    //针对所有测试，只执行一次，且必须为static void
    @AfterClass
    public static void afterClass(){
    }

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