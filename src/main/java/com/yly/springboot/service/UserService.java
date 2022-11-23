package com.yly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.springboot.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Service 这个注解是将这个类注入到spring容器
 */
@Service
public interface UserService extends IService<User> {
}
