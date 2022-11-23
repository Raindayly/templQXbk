package com.yly.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.springboot.common.Constants;
import com.yly.springboot.entity.Dict;
import com.yly.springboot.entity.MenuAndId;
import com.yly.springboot.entity.User;
import com.yly.springboot.mapper.DictMapper;
import com.yly.springboot.service.CurrentUserInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import com.yly.springboot.service.IMenuService;
import com.yly.springboot.mapper.MenuMapper;
import com.yly.springboot.common.Result;
import com.yly.springboot.entity.Menu;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yly
 * @since 2022-08-01
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    CurrentUserInfo currentUserInfo;

    @Resource
    IMenuService IMenuService;

    @Resource
    MenuMapper MenuMapper;

    @Resource
    DictMapper dictMapper;
    @GetMapping("/allmenusandid")
    public Result getMenusAndIds(){
        List<MenuAndId> menuAndId = MenuMapper.getMenuAndId();
        return Result.success(menuAndId);
    }

    @GetMapping("/allmenus")
    public Result getTree() {
        List<Menu> allResultMenus = currentUserInfo.getAllResultMenus();
//        List<Menu> treeList = IMenuService.list();
//        List<Menu> parentNode = treeList.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
//        for (Menu menu: parentNode) {
//            menu.setChildren(treeList.stream().filter(m->menu.getId().equals(m.getPid())).collect(Collectors.toList()));
//        }
        return Result.success(allResultMenus);
    }

    @GetMapping
    public Result getAll(Integer pageNum,Integer pageSize,@RequestParam String name) {
        QueryWrapper<Menu> mySelectPageQueryWrapper=new QueryWrapper<>();
        if(!name.equals("")){
            mySelectPageQueryWrapper.eq("name",name);
        }
        mySelectPageQueryWrapper.isNull("pid");
        Page<Menu> page = new Page<>(pageNum,pageSize);
        Page<Menu> menuPage = MenuMapper.selectPage(page, mySelectPageQueryWrapper);
//        List<Menu> list = IMenuService.list();
//        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
//
//        for (Menu menu: parentNode) {
//                menu.setChildren(list.stream().filter(m->menu.getId().equals(m.getPid())).collect(Collectors.toList()));
//        }
        List<Menu> allResultMenus = currentUserInfo.getAllResultMenus();
        menuPage.setRecords(allResultMenus);
        return Result.success(menuPage);
    }
    @PostMapping("/save")
    public Result save(@RequestBody Menu menu) {
        return Result.success(IMenuService.saveOrUpdate(menu));
    }

    @GetMapping("/{id}")
    public Result info(@PathVariable String id){
        return Result.success(IMenuService.getById(id));
    }

    @PostMapping("/delete")
    public Result info(@RequestBody List<String> ids) {
        return Result.success(IMenuService.removeByIds(ids));
    }

    @GetMapping("/page")
    public Result page(Integer pageNum,Integer pageSize,@RequestParam String name){
        QueryWrapper<Menu> mySelectPageQueryWrapper=new QueryWrapper<>();
        Page<Menu> page = new Page<>(pageNum,pageSize);
        if(!name.equals("")){
            mySelectPageQueryWrapper.eq("name",name);
        }
        return Result.success(MenuMapper.selectPage(page,mySelectPageQueryWrapper));
    }

    @GetMapping("/icons")
    Result getIcon(){
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("type", Constants.Dict_TYPE_ICON);

        return Result.success(dictMapper.selectList(dictQueryWrapper));
    }
}

