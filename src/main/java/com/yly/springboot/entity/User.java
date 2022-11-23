package com.yly.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@TableName("sys_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String username;
    private String password;
    private String nickname;

    private String email;
    private String iphone;
    private String address;
    private Date createTime;
    private Date updateTime;
    private String role;
    private String avatarUrl;
}
