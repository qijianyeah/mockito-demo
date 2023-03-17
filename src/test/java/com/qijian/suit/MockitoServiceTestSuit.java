package com.qijian.suit;

import com.qijian.service.Impl.StudentController2Test;
import com.qijian.service.Impl.UserServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author mahuahong
 * @Date 2023/3/17 17:03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({StudentController2Test.class, UserServiceImplTest.class})
public class MockitoServiceTestSuit {
}
