package com.qijian.conteoller;

import com.qijian.base.ResponseData;
import com.qijian.po.User;
import com.qijian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    /**
     *
     */
    @PostMapping("/getLisstByage")
    public ResponseData<List<User>> getUserLessThan(@RequestBody User user){
        ResponseData<List<User>> responseData = service.listForLessThan(user);
        return responseData;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.list();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

}
