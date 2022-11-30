package com.yly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service 这个注解是将这个类注入到spring容器
 */
@Service
public interface UserService extends IService<User> {
    String getCurrentToken();
    String getUserId();
    User getUser();
    String getRole();
    String getRoleId();
    List<String> getMenuIds();
    List<Menu> getAllMenus();
    List<Menu> getAllResultMenus();
    List<Menu> getCurrentMenus();
    Boolean isUserHas(String userName);

}
