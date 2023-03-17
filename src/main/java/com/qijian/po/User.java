package com.qijian.po;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 ： 1 男 ； 0 女
     */
    private Integer gender;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                "，gender=" + gender +
                '}';
    }
}
