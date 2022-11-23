package com.yly.springboot.mapper;

import com.yly.springboot.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yly.springboot.entity.MenuAndId;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yly
 * @since 2022-08-01
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuAndId> getMenuAndId();
}
