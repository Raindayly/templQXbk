package com.yly.springboot.service.Impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.springboot.Utils.MenuFilter;
import com.yly.springboot.Utils.MenuGenerator;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.RoleMenuRelation;
import com.yly.springboot.entity.User;
import com.yly.springboot.mapper.RoleMapper;
import com.yly.springboot.mapper.UserMapper;
import com.yly.springboot.service.IMenuService;
import com.yly.springboot.service.IRoleMenuRelationService;
import com.yly.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserService userService;

    @Resource
    RoleMapper roleMapper;

    @Resource
    IMenuService iMenuService;
    @Resource
    UserMapper userMapper;



    @Resource
    IRoleMenuRelationService iRoleMenuRelationService;


    @Override
    public String getCurrentToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        return token;
    }

    @Override
    public String getUserId() {
        String userId = JWT.decode(getCurrentToken()).getAudience().get(0);
        return userId;
    }

    @Override
    public User getUser() {
        return userService.getById(getUserId());
    }

    @Override
    public String getRole() {
        User user = userService.getById(getUserId());
        return user.getRole();
    }

    @Override
    public String getRoleId() {
        return roleMapper.selectIdByRole(getRole());
    }

    @Override
    public List<String> getMenuIds() {
        QueryWrapper<RoleMenuRelation> roleMenuRelationQueryWrapper = new QueryWrapper<>();
        roleMenuRelationQueryWrapper.select("menuid");
        roleMenuRelationQueryWrapper.eq("roleid",getRoleId());
        List<RoleMenuRelation> list = iRoleMenuRelationService.list(roleMenuRelationQueryWrapper);
        List<String> MenuIds = list.stream().map(v -> v.getMenuid()).collect(Collectors.toList());
        return MenuIds;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        List<String> menuIds = getMenuIds();
        menuIds.forEach(menuId->{
            menus.add(iMenuService.getById(menuId));
        });
        return menus;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Menu> getAllResultMenus() {
        List<Menu> rootMenus = iMenuService.getRootMenus();
        return MenuGenerator.getMenuList(rootMenus);
    }

    /**
     *
     * @return 使用角色过滤菜单
     */
    @Override
    public List<Menu> getCurrentMenus() {
        List<String> menuIds = getMenuIds();
        List<Menu> allResultMenus = getAllResultMenus();
        MenuFilter.menuFilter(allResultMenus,menuIds);
        return allResultMenus;
    }

    @Override
    public Boolean isUserHas(String userName) {
        return userMapper.getOneByUsername(userName)>0;
    }
}
