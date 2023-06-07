package com.scut.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.scut.backend.bean.User;
import com.scut.backend.mapper.UserMapper;
import com.scut.backend.utils.Result;
import com.scut.backend.utils.ResultUtils;
import com.scut.backend.utils.SaltPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
@CrossOrigin
public class UserController {

    @Autowired
    UserMapper userMapper;


    //登录成功返回token
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String password = (String)requestMap.get("password");

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name",name);
        User user = userMapper.selectOne(qw);
        System.out.println(user);

        if(user == null) {
            return ResultUtils.error(100,"用户不存在");
        }
        if(SaltPasswordUtil.isMatch(password,user.getToken())) {
            return ResultUtils.success(user.getToken());
        }
        return ResultUtils.error(101,"密码错误");
    }


    @RequestMapping("/registe")
    @ResponseBody
    public Result registe(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String password = (String)requestMap.get("password");

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name",name);
        User user = userMapper.selectOne(qw);
        System.out.println(user);

        if(user != null) {
            return ResultUtils.error(102,"用户已存在");
        }
        User addUser = User.builder().name(name).token(SaltPasswordUtil.getToken(password)).build();
        userMapper.insert(addUser);
        return ResultUtils.success(addUser.getToken());
    }

    @RequestMapping("/getAllUserName")
    @ResponseBody
    public Result getAllUserName(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");

        QueryWrapper<User> qw = new QueryWrapper<>();
        List<User> users = userMapper.selectList(qw);
        String[] names = users.stream()
                .filter((user)->{
                    if(user.getName().equals(name)) {
                        return false;
                    }
                    return true;
                })
                .map(User::getName)
                .toArray(String[]::new);
        return ResultUtils.success(names);
    }
}
