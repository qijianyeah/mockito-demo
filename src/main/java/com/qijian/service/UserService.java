package com.qijian.service;

import com.qijian.base.ResponseData;
import com.qijian.po.User;
import com.qijian.dto.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * 查询低于某个年龄的User
     * @param user
     * @return
     */
    ResponseData<List<User>> listForLessThan(User user);

    //    通过用户名获取用户列表信息
    public List<UserDTO> getUsersByName(String userName);

    //    查询所有
    List<User> list();
    //    通过id查询
    User getById(Integer id);

    //    添加用户
    Boolean add(User user);

    //    更新用户
    Boolean update(User user);

    //    通过id删除
    Boolean deleteById(Integer id);


}
