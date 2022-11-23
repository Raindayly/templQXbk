package com.yly.springboot.service;


import com.yly.springboot.Utils.TokenUtil;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.User;

import java.util.List;

public interface CurrentUserInfo  {
    String getCurrentToken();
    String getUserId();
    User getUser();
    String getRole();
    String getRoleId();
    List<String> getMenuIds();
    List<Menu> getAllMenus();
    List<Menu> getAllResultMenus();
    List<Menu> getCurrentMenus();

}
