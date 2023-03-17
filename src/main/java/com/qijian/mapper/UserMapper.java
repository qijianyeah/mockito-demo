package com.qijian.mapper;
import com.qijian.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<User> pages(Map<String, Object> map);

    List<User> list();

    User getById(Integer id);

    Integer add(User user);

    Integer update(User user);

    Integer deleteById(Integer id);

    List<User> listForLessThan(Integer age);
}
