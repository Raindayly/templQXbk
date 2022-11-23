package com.yly.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@TableName("sys_user")
public class UserDTO {
    private String username;
    private String nickname;
    private String password;
    private String token;
    private String avatarUrl;
    private String role;
    private List<Menu> menus;
}
