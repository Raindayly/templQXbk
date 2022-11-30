package com.yly.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.springboot.Utils.TokenUtil;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.common.Result_Code;
import com.yly.springboot.entity.User;
import com.yly.springboot.mapper.UserMapper;
import com.yly.springboot.service.CurrentUserInfo;
import com.yly.springboot.service.LoginService;
import com.yly.springboot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Resource
    private CurrentUserInfo currentUserInfo;

    @GetMapping
    public Result getUser() {
        return new ResultUtil<>().setData(userService.list());
    }


    @GetMapping("/page")
    public Result page(Integer pageNum,Integer pageSize,String username,String address,String email) {
        QueryWrapper<User> mySelectPageQueryWrapper = new QueryWrapper<>();
        if(!username.equals("")){
            mySelectPageQueryWrapper.like("username",username);
        }
        if(!address.equals("")){
            mySelectPageQueryWrapper.like("address",address);

        }
        if(!email.equals("")){
            mySelectPageQueryWrapper.like("email",email);
        }

        Page<User> page = new Page<>(pageNum,pageSize);
        return new ResultUtil<>().setData(userMapper.selectPage(page,mySelectPageQueryWrapper));
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {

        return new ResultUtil<>().setData(userService.saveOrUpdate(user));
    }
    @GetMapping("/info")
    public Result info(@RequestParam("id")String id) {

        return new ResultUtil<>().setData(userService.getById(id));
    }

    @PostMapping("/delete")
    public Result info(@RequestBody List<String> ids) {
        if(userService.removeByIds(ids)){
            return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_200.getMsg());
        }else{
            return new ResultUtil<>().setData(Result_Code.CODE_600.getCode(),"删除失败,请确定参数正确");
        }
    }
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws Exception {
        List<User> list = userService.list();
        ExcelWriter writer = ExcelUtil.getWriter();

        String filename = URLEncoder.encode("用户管理", "UTF-8");

        response.setHeader("Content-Disposition","attachment;filename="+filename+".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");

        writer.write(list);
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream);
        writer.close();
        outputStream.close();
        return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_200.getMsg());
    }
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<User> list = reader.readAll(User.class);

        return new ResultUtil<>().setData(userService.saveOrUpdateBatch(list));
    }
    @GetMapping("/username/{username}")
    public Result userInfoByName(@PathVariable String username) {
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", username);
        User one = userService.getOne(objectQueryWrapper);
        return new ResultUtil<>().setData(one);
    }
    @GetMapping("/userinfo")
    public Result selectUserInfo() {
        return new ResultUtil<>().setData(TokenUtil.getCurrentUser());
    }

    @GetMapping("/save_role")
    public Result saveUserRole(@RequestParam("role") String role ,@RequestParam("id") String id) {
        User user = userService.getById(id);
        user.setRole(role);
        userService.updateById(user);
        return new ResultUtil().setSuccessMsg(Result_Code.CODE_200.getMsg());
    }

}
