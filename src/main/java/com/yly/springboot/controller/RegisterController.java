package com.yly.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yly.springboot.common.Constants;
import com.yly.springboot.common.Result;
import com.yly.springboot.entity.User;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.service.LoginService;
import com.yly.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public Result register(@RequestBody UserDTO userDTO){
        try {
            UserDTO data = loginService.register(userDTO);
            return Result.success(data);

        }catch (Exception e){
            return Result.error(Constants.CODE_600,e.getMessage());
        }

    }
}
