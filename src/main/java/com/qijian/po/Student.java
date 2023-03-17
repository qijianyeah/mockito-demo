package com.qijian.po;

import lombok.*;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student {

    /**
     * id
     */
    private Integer id;

    /**
     * 学号
     */
    private String num;

    /**
     * 班级号码
     */
    private Integer classNum;

    /**
     * 寝室号
     */
    private Integer dormitoryNum;

    /**
     * 用户ID
     */
    private Integer userId;


}
