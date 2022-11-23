package com.yly.springboot.Utils;

import com.yly.springboot.entity.Menu;
import com.yly.springboot.service.IMenuService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuGenerator {

    private static IMenuService staticMenuService;

    @Resource
    IMenuService iMenuService;

    @PostConstruct
    public void setSevice(){
        staticMenuService = iMenuService;
    }

    /**
     *
     * @param menus 所有一级菜单
     * @return 返回一整个菜单树
     */
    public static List<Menu> getMenuList(List<Menu> menus) {
        List<Menu> resultMenus = new ArrayList<>();
        for (Menu menu:menus) {
            int hasChildren = menu.getHasChildren();
            if (hasChildren == 1) {
                menu.setChildren(getMenuList(staticMenuService.getMenusByPid(menu.getId())));
            }else {
                menu.setChildren(new ArrayList<>());
            }
            resultMenus.add(menu);
        }
        return resultMenus;
    }

}
