package com.yly.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.common.Result_Code;
import com.yly.springboot.common.interceptor.classDefinition.MenuOfRoleObj;
import com.yly.springboot.entity.Role;
import com.yly.springboot.entity.RoleMenuRelation;
import com.yly.springboot.entity.User;
import com.yly.springboot.service.IRoleMenuRelationService;
import org.springframework.web.bind.annotation.*;
import com.yly.springboot.service.IRoleService;
import com.yly.springboot.mapper.RoleMapper;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yly
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    IRoleService IRoleService;

    @Resource
    RoleMapper RoleMapper;

    @Resource
    IRoleMenuRelationService iRoleMenuRelationService;

    @GetMapping
    public Result getAll() {
        return new ResultUtil<>().setData(IRoleService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Role role) {

        return new ResultUtil<>().setData(IRoleService.saveOrUpdate(role));
    }

    @GetMapping("/{id}")
    public Result info(@PathVariable String id){
        return new ResultUtil<>().setData(IRoleService.getById(id));
    }

    @GetMapping("/menu/{id}")
    public Result Menuinfo(@PathVariable String id){
        QueryWrapper<RoleMenuRelation> roleMenuRelationQueryWrapper = new QueryWrapper<>();
        roleMenuRelationQueryWrapper.eq("roleid",id);
        roleMenuRelationQueryWrapper.select("menuid");
        List<RoleMenuRelation> list = iRoleMenuRelationService.list(roleMenuRelationQueryWrapper);
        List<String> collect = list.stream().map(v -> v.getMenuid()).collect(Collectors.toList());
        return new ResultUtil<>().setData(collect);
    }

    @PostMapping("/delete")
    public Result info(@RequestBody List<String> ids) {
        return new ResultUtil<>().setData(IRoleService.removeByIds(ids));
    }

    @GetMapping("/page")
    public Result page(Integer pageNum,Integer pageSize, @RequestParam String name){
        QueryWrapper<Role> mySelectPageQueryWrapper=new QueryWrapper<>();

        Page<Role> page = new Page<>(pageNum,pageSize);
        if (!name.equals("")) {
            mySelectPageQueryWrapper.like("name", name);
        }
        Page<Role> rolePage = RoleMapper.selectPage(page, mySelectPageQueryWrapper);

        return new ResultUtil<>().setData(rolePage);
    }

    @PostMapping("/savemenu")
    Result saveMenu(@RequestBody MenuOfRoleObj roleMenuObj){
        String currentRoleId = roleMenuObj.getCurrentRoleId();
        List<String> currentRoleMenuIdArr = roleMenuObj.getCurrentRoleMenuIdArr();
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("roleid",currentRoleId);
        iRoleMenuRelationService.remove(queryWrapper);
        currentRoleMenuIdArr.stream().forEach(v->{
            QueryWrapper<RoleMenuRelation> roleMenuRelationQueryWrapper = new QueryWrapper<>();

            roleMenuRelationQueryWrapper.eq("roleid",currentRoleId);
            roleMenuRelationQueryWrapper.eq("menuid",v);
            RoleMenuRelation one = iRoleMenuRelationService.getOne(roleMenuRelationQueryWrapper);
            if(one==null){
                RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
                roleMenuRelation.setRoleid(currentRoleId);
                roleMenuRelation.setMenuid(v);
                iRoleMenuRelationService.save(roleMenuRelation);
            }
        });
        return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_200.getMsg());
    }
}

