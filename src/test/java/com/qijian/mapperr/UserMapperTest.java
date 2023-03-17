package com.qijian.mapperr;

import com.qijian.mapper.UserMapper;
import com.qijian.po.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采用内存数据库进行mybatis mapper单元测试
 * 优点：对实际的数据库无任何影响
 * 确点：必须提供h2初始化语句，同时依赖于h2的 sdk
 *
 * @author mahuahong
 * @Date 2023/3/17 17:12
 */
@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(properties = {
        "spring.datasource.schema = classpath:schema-h2.sql"
})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    private User user;

    @Before
    public void init(){
        user = User.builder()
                .id(6)
                .name("qijian")
                .age(18)
                .gender(1)
                .build();
        userMapper.add(user);
    }

    @Test
    public void testList(){
        List<User> list = userMapper.list();
        Assert.assertEquals(list.size(),6);
    }


    @Test
    public void testListForLessThan(){

    }


}
