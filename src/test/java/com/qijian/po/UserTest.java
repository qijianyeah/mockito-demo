package com.qijian.po;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author mahuahong
 * @Date 2023/3/20 00:47
 */
public class UserTest {

    @InjectMocks
    User user;

    @Before
    public void init() {
        user = User.builder()
                .id(1)
                .gender(1)
                .name("qijian")
                .age(18)
                .build();
    }

    //测试getter方法
    @Test
    public void testGetName() {
        Object result = ReflectionTestUtils.invokeMethod(user, "getName");
        Assertions.assertThat(result).isEqualTo("qijian");
    }

    //测试setter方法
    @Test
    public void testSetName() {
        Object result = ReflectionTestUtils.invokeMethod(user, "setName","tom");
        Assertions.assertThat(user.getName()).isEqualTo("tom");
    }
}