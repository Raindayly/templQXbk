package com.yly.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yly.springboot.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    int getOneByUsername(@Param(value="username") String username);
}
