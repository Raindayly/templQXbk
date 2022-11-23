package com.yly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.springboot.entity.User;
import com.yly.springboot.entity.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService extends IService<User> {
    UserDTO login(UserDTO userDTO);
    UserDTO register(UserDTO userDTO);
    String getToken(UserDTO userDTO);

}
