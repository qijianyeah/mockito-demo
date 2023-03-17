package com.qijian.service.Impl;

import com.qijian.base.ResponseData;
import com.qijian.mapper.UserMapper;
import com.qijian.po.User;
import com.qijian.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseData<List<User>> listForLessThan(User user){
        if (Objects.isNull(user)){
            return ResponseData.errorWithMeg("error");
        }
        if (user.getAge() == null){
            return ResponseData.errorWithMeg("输入的age不能为bull");
        }
        if (user.getAge() <= 0 || user.getAge() > 120){
            return ResponseData.errorWithMeg("输入的age需要大于0");
        }
        List<User> userList = userMapper.listForLessThan(user.getAge());
        return ResponseData.ok("成功",userList);
    }


    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }


    @Override
    public Boolean add(User user) {
        return userMapper.add(user) == 1;
    }

    @Override
    public Boolean update(User user) {
        return userMapper.update(user) == 1;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public List<User> pages(Integer index, Integer size, String search) {
        Map<String, Object> map = new HashMap<>();
//        传入的页数减一乘上条数就是当前页数对应的数据
        map.put("index", (index - 1) * size);
        map.put("size", size);
        map.put("search", search);
        return userMapper.pages(map);
    }
}
