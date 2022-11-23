package com.yly.springboot.Utils;

import com.yly.springboot.entity.Menu;

import java.util.List;

public class MenuFilter {
    public static void menuFilter(List<Menu> Menus,List<String> menuIds){
         menuFilter_2(menuFilter_1(Menus,menuIds));
    }
    public static List<Menu> menuFilter_1(List<Menu> Menus,List<String> menuIds){
        //第一步过滤, 会将没有选择子级的父级保留下来
        Menus.removeIf(child->(!menuIds.contains(child.getId())&&child.getHasChildren()==0));
        for (Menu menu: Menus) {
            List<Menu> children = menu.getChildren();
            if(children.size()!=0){
                menuFilter_1(children,menuIds);
            }
        }
        return Menus;
    }
    public static void menuFilter_2(List<Menu> Menus){
        //第二步过滤, 过滤没有选择子级的父级
        Menus.removeIf(child->(child.getChildren().size()==0&&child.getHasChildren()==1));
        for (Menu menu: Menus) {
            List<Menu> children = menu.getChildren();
            if(children.size()!=0){
                menuFilter_2(children);
            }
        }
    }

}
