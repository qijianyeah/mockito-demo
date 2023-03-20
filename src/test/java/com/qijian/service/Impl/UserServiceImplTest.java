package com.qijian.service.Impl;

import com.qijian.base.ResponseData;
import com.qijian.mapper.UserMapper;
import com.qijian.po.User;
import com.qijian.service.UserService;
import com.qijian.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test; //错误
import org.junit.Test;//注意：
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.assertThrows;


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
//        Whitebox.setInternalState(testService,"userMapper",userMapper);
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


    //覆盖除了Catch内的代码（先测试覆盖没有异常的代码）
    @Test
    public void testGetUsers_listIsNotNUll() {
        List<UserDTO> userDtos = new ArrayList<>();
        userDtos.add(UserDTO.builder().build());
        userDtos.add(UserDTO.builder().build());
        PowerMockito.when(userMapper.getUsers("test")).thenReturn(userDtos);
        Assertions.assertThat(userMapper.getUsers("test")).isEqualTo(userDtos);
        //以上覆盖除了Catch内的代码（先测试覆盖没有异常的代码）
    }

    // 测试抛出异常代码
    @Test
    public void testGetUsersByNameWithListIsNUll(){
        //a.对异常打桩
        DataAccessException exception = PowerMockito.mock(DataAccessException.class);
        //b.模拟try内的方法，doThrow异常
//        PowerMockito.doThrow(exception).when(userMapper.getUsers("test"));
        PowerMockito.doThrow(exception).when(userMapper).getUsers("test");
        //c.验证异常后返回的结果
        Assertions.assertThat(CollectionUtils.isEmpty(userMapper.getUsers("test"))).isTrue();
    }

    @Test
    public void testGetUsersByNameWithException() {
        DataAccessException exception = PowerMockito.mock(DataAccessException.class);
        // Arrange
        String userName = "testUser";
        UserDTO userDto = UserDTO.builder().build();
        userDto.setName(userName);
        List<UserDTO> userDtos = Arrays.asList(userDto);
        UserMapper userMapperMock = PowerMockito.mock(UserMapper.class);
        PowerMockito.when(userMapperMock.getUsers(userName)).thenThrow(exception);
        // Act
        List<UserDTO> result = testService.getUsersByName(userName);
        // Assert
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void mock_element_test_05() {
        LinkedList mockedList = PowerMockito.mock(LinkedList.class);
        PowerMockito.doThrow(new ArithmeticException()).when(mockedList).clear();

        // 调用mockedList.clear()方法的时候会抛出异常RuntimeException
        assertThrows(ArithmeticException.class, mockedList::clear);

        // thenThrow 中可以指定多个异常。在调用时异常依次出现。若调用次数超过异常的数量，再次调用时抛出最后一个异常。
        Random mockRandom = PowerMockito.mock(Random.class);
        PowerMockito.when(mockRandom.nextInt()).thenThrow(new ArithmeticException("异常1"), new RuntimeException("异常2"));
        assertThrows(ArithmeticException.class, mockRandom::nextInt);
        assertThrows(RuntimeException.class, mockRandom::nextInt);


        // 对于返回类型是 void 的函数，thenThrow 是无效的，要使用 doThrow。
        ExampleService exampleService = PowerMockito.mock(ExampleService.class);
        // 这种写法可以达到效果
        PowerMockito.doThrow(new RuntimeException("异常")).when(exampleService).hello();
        assertThrows(RuntimeException.class, exampleService::hello);
    }

    static class ExampleService {

        public void hello() {
            System.out.println("Hello");
        }

    }

}