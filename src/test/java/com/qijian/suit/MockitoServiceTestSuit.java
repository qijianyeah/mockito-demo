package com.qijian.suit;

import com.qijian.controller.StudentControllerTest;
import com.qijian.service.Impl.UserServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * 创建测试套件
 * @author mahuahong
 * @date 2023/3/17 17:03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({StudentControllerTest.class, UserServiceImplTest.class})
public class MockitoServiceTestSuit {
}
