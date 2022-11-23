package com.yly.springboot.service;

import com.yly.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yly
 * @since 2022-08-01
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenusByPid(String pid);
    List<Menu> getRootMenus();
}
