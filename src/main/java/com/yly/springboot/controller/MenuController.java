package com.yly.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.entity.Dict;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.MenuAndId;
import com.yly.springboot.mapper.DictMapper;
import com.yly.springboot.mapper.MenuMapper;
import com.yly.springboot.service.CurrentUserInfo;
import com.yly.springboot.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<Object> getMenusAndIds(){
        List<MenuAndId> menuAndId = MenuMapper.getMenuAndId();
        return new ResultUtil<>().setData(menuAndId);
    }


    @GetMapping("/allmenus")
    public Result<Object> getTree() {
        List<Menu> allResultMenus = currentUserInfo.getAllResultMenus();
        return new ResultUtil<>().setData(allResultMenus);
    }

    @GetMapping
    public Result<Object> getAll(Integer pageNum, Integer pageSize, @RequestParam String name) {
        QueryWrapper<Menu> mySelectPageQueryWrapper=new QueryWrapper<>();
        if(!name.equals("")){
            mySelectPageQueryWrapper.eq("name",name);
        }
        mySelectPageQueryWrapper.isNull("pid");
        Page<Menu> page = new Page<>(pageNum,pageSize);
        Page<Menu> menuPage = MenuMapper.selectPage(page, mySelectPageQueryWrapper);
        List<Menu> allResultMenus = currentUserInfo.getAllResultMenus();
        menuPage.setRecords(allResultMenus);
        return new ResultUtil<>().setData(menuPage);
    }
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Menu menu) {
        return new ResultUtil<>().setData(IMenuService.saveOrUpdate(menu));
    }

    @GetMapping("/{id}")
    public Result<Object> info(@PathVariable String id){
        return new ResultUtil<>().setData(IMenuService.getById(id));
    }

    @PostMapping("/delete")
    public Result<Object> info(@RequestBody List<String> ids) {
        return new ResultUtil<>().setData(IMenuService.removeByIds(ids));
    }

    @GetMapping("/page")
    public Result<Object> page(Integer pageNum, Integer pageSize, @RequestParam String name){
        QueryWrapper<Menu> mySelectPageQueryWrapper=new QueryWrapper<>();
        Page<Menu> page = new Page<>(pageNum,pageSize);
        if(!name.equals("")){
            mySelectPageQueryWrapper.eq("name",name);
        }
        return new ResultUtil<>().setData(MenuMapper.selectPage(page,mySelectPageQueryWrapper));
    }

    @GetMapping("/icons")
    Result<Object> getIcon(){
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("type", "");
        return new ResultUtil<>().setData(dictMapper.selectList(dictQueryWrapper));
    }
}

