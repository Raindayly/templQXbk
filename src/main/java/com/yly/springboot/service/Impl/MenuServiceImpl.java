package com.yly.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.mapper.MenuMapper;
import com.yly.springboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yly
 * @since 2022-08-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    public List<Menu> getMenusByPid(String pid) {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("pid",pid);
        return list(menuQueryWrapper);
    }

    @Override
    public List<Menu> getRootMenus() {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("level",1);
        return list(menuQueryWrapper);
    }
}
