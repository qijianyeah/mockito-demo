package com.qijian.po;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author mahuahong
 * @Date 2023/3/16 23:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    /**
     * id
     */
    private String id;

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


}
