package com.qijian;

import com.qijian.po.User;
import com.qijian.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class MockitoDemoApplicationTests {

    @Autowired
    UserService service;

    @Test
    void contextLoads() {
//        分页查询

    }
}
