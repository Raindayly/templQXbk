package com.yly.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.common.Result_Code;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.service.LoginService;
import com.yly.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();

        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_603.getMsg());
        }

        //判断用户是否存在
        if(!userService.isUserHas(username)){
            return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_602.getMsg());
        }

        String token = loginService.getToken(userDTO);

        if(token == null){
            return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_601.getMsg());
        }

        return new ResultUtil<>().setData(token);
    }
}
