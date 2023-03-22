package com.qijian.util;

import com.qijian.service.Impl.StudentServiceImpl;
import com.qijian.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



/**
 * @author mahuahong
 * @Date 2023/3/21 17:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ XxxUtils.class })
public class XxxUtilsTest {
    
    @InjectMocks
    private XxxUtils xxxUtils = new XxxUtils();

    @Test
    public void testXxxMethod(){
        boolean b = XxxUtils.xxxMethod(false);
    }




}