package com.qijian.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author mahuahong
 * @Date 2023/3/17 00:39
 */
@Data
@Builder
public class UserDTO {
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
}
