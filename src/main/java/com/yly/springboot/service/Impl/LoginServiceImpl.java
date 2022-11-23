package com.yly.springboot.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.springboot.Utils.TokenUtil;
import com.yly.springboot.common.Constants;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.RoleMenuRelation;
import com.yly.springboot.entity.User;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.mapper.RoleMapper;
import com.yly.springboot.mapper.RoleMenuRelationMapper;
import com.yly.springboot.mapper.UserMapper;
import com.yly.springboot.service.CurrentUserInfo;
import com.yly.springboot.service.IMenuService;
import com.yly.springboot.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Resource
    RoleMenuRelationMapper roleMenuRelationMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    IMenuService iMenuService;

    @Resource
    CurrentUserInfo currentUserInfo;

    /**
     *
     * @param userDTO
     * @return localstorage前端存储
     */
    public UserDTO login(UserDTO userDTO) {
        User result = getUserInfo(userDTO);
        if (result != null) {
            String token = TokenUtil.genToken(result.getId(), result.getPassword());
            String role = result.getRole();
            String roleid = roleMapper.selectIdByRole(role);
            userDTO.setToken(token);
            //获取某个角色对应的菜单集合
//            List<String> menuIds = roleMenuRelationMapper.selectMenusByRoleId(roleid);
//            //获取menus
//            List<Menu> menuslist = iMenuService.list();
//            List<Menu> parentNode = menuslist.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
//            for (Menu menu: parentNode) {
//                menu.setChildren(menuslist.stream().filter(m->menu.getId().equals(m.getPid())).collect(Collectors.toList()));
//            }

            userDTO.setMenus(currentUserInfo.getCurrentMenus());
            BeanUtil.copyProperties(result, userDTO, true);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

    }

    public UserDTO register(UserDTO userDTO) {


        User result = getUserInfo(userDTO);
        if (result == null) {
            result = new User();

            BeanUtil.copyProperties(userDTO, result, true);
            save(result);
            return login(userDTO);

        } else {
            throw new ServiceException(Constants.CODE_600, "用户已注册");

        }


    }

    @Override
    public String getToken(UserDTO userDTO) {
        User result = getUserInfo(userDTO);
        String token = null;
        if (result != null) {
            token = TokenUtil.genToken(result.getId(), result.getPassword());}
        return token;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", userDTO.getUsername());
        objectQueryWrapper.eq("password", userDTO.getPassword());
        User result;
        try {
            result = getOne(objectQueryWrapper);
            return result;
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
    }

    public List<Menu> getMenuList(List<Menu> parentNode,List<String> menuIds){
        List<Menu> menusOfRoleList = new ArrayList<>();

        //过滤菜单  对比角色菜单表结果menuIds 补全缺少的菜单,
        //一个是树的返回值, 新建个数组做个过滤
        //一个是菜单的返回,返回补全后的就可以了
        //要补全的情况:前端选择了子集没有选父级的时候要补齐
        for(Menu menu : parentNode){
            Boolean isChildrenOfAllMenuBlank =  menu.getChildren().size()==0;
            Boolean isTreeSelectChildren = false;
            if(!isChildrenOfAllMenuBlank){
                //判断前端传来的树数组里是否有被选择的子级
                List<String> sourceMenuIds = iMenuService.getMenusByPid(menu.getId()).stream().map(v -> v.getId()).collect(Collectors.toList());
                for (String menuId : menuIds) {
                    if(sourceMenuIds.contains(menuId)) {
                        isTreeSelectChildren = true;
                    }
                }
            }
            Boolean isTreeSelectFather = menuIds.contains(menu.getId());
            //判断子级是否为空
            if(!isChildrenOfAllMenuBlank){
                //不为空判断逻辑:前端选择了子集,前端没有选父级
                if(isTreeSelectChildren && !isTreeSelectFather) {
                    menusOfRoleList.add(menu);
                    //前端选择了子级并且也选择了父级 全选
                }else if(isTreeSelectChildren && isTreeSelectFather){
                    menusOfRoleList.add(menu);
                }
            }
            //前端选择了父级,但是这个菜单本就没有子级
            if(isTreeSelectFather && isChildrenOfAllMenuBlank){
                menusOfRoleList.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child->!menuIds.contains(child.getId()));
        }

        return menusOfRoleList;
    }
}
