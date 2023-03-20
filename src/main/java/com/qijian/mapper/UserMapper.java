package com.qijian.mapper;
import com.qijian.po.User;
import com.qijian.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> list();

    User getById(Integer id);

    Integer add(User user);

    Integer update(User user);

    Integer deleteById(Integer id);

    List<User> listForLessThan(Integer age);

    List<UserDTO> getUsers(String userName);
}
