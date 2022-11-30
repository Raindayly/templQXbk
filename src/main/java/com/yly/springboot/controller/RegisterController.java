package com.yly.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.common.Result_Code;
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
            return new ResultUtil<>().setData(data);
        }catch (Exception e){
            return new ResultUtil<>().setData(Result_Code.CODE_600.getCode(),e.getMessage());
        }

    }
}
