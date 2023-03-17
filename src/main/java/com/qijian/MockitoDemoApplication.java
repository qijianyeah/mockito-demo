package com.qijian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qijian.mapper")
public class MockitoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockitoDemoApplication.class, args);
    }

}
