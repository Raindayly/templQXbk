package com.yly.springboot.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yly.springboot.common.Constants;
import com.yly.springboot.common.Result;
import com.yly.springboot.entity.User;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/charts")
public class ChartsController {

    @Autowired
    UserService userService;

    @GetMapping
    Result getCharts() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> userList;
        try {
            userList = userService.list();
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_600, "用户信息丢失,请联系管理员");
        }
        HashMap<String, Integer> userInfoMap = new HashMap<>();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user : userList) {
            Date userCreateTime = user.getCreateTime();
//            Date userUpdateTime = user.getUpdateTime();

            Quarter quarter = DateUtil.quarterEnum(userCreateTime);
//            DateUtil.quarterEnum(userUpdateTime);

            switch (quarter){
                case Q1: q1++ ; break;
                case Q2: q2++ ; break;
                case Q3: q3++ ; break;
                case Q4: q4++ ; break;
                default:break;
            }
        }

        userInfoMap.put("q1", q1);
        userInfoMap.put("q2", q2);
        userInfoMap.put("q3", q3);
        userInfoMap.put("q4", q4);
        return Result.success(userInfoMap);
    }

    @GetMapping("/recently")
    Result getHebdomadUserData(){
        DateTime nowDate = DateUtil.date();
        HashMap<String, List> stringListHashMap = new HashMap<>();
        ArrayList<Integer> hebdomadUserDataNum = new ArrayList<>();
        ArrayList<String> xAxisDataList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DateTime dateTime = DateUtil.offsetDay(nowDate, -i);

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.between("create_time",DateUtil.beginOfDay(dateTime),DateUtil.endOfDay(dateTime));
            List<User> list = userService.list(userQueryWrapper);
            xAxisDataList.add(DateUtil.formatDate(dateTime));
            hebdomadUserDataNum.add(list.size());
        }
        stringListHashMap.put("xData",xAxisDataList);
        stringListHashMap.put("yData",hebdomadUserDataNum);
        return Result.success(stringListHashMap);
    }
}
