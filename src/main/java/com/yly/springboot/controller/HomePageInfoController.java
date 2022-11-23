package com.yly.springboot.controller;

import com.yly.springboot.common.Result;
import com.yly.springboot.mapper.FileMapper;
import com.yly.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/home")
public class HomePageInfoController {

    @Autowired
    UserService userService;
    @Resource
    FileMapper fileMapper;

    @GetMapping("/homepageinfo")
    Result fetchHomePageInfo(){
        HashMap<String, Object> stringIntegerHashMap = new HashMap<>();
        Long count = userService.count();
        Long aLong = fileMapper.selectCount(null);
        stringIntegerHashMap.put("allVipNum",count);
//        stringIntegerHashMap.put("todayLoginedUserNum")
        stringIntegerHashMap.put("allFileNum",aLong);

        return Result.success(stringIntegerHashMap);
    }
}
