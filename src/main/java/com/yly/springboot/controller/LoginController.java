package com.yly.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.yly.springboot.Utils.TokenUtil;
import com.yly.springboot.common.Constants;
import com.yly.springboot.common.Result;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,Constants.CODE_400_msg);
        }
        String token = loginService.getToken(userDTO);

//        UserDTO vo = loginService.login(userDTO);

        return Result.success(token);
    }
}
