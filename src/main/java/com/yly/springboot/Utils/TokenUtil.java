package com.yly.springboot.Utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yly.springboot.common.Result;
import com.yly.springboot.entity.Menu;
import com.yly.springboot.entity.RoleMenuRelation;
import com.yly.springboot.entity.User;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.mapper.RoleMapper;
import com.yly.springboot.mapper.RoleMenuRelationMapper;
import com.yly.springboot.service.CurrentUserInfo;
import com.yly.springboot.service.IMenuService;
import com.yly.springboot.service.UserService;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TokenUtil  {

    private static CurrentUserInfo staticCurrentUserInfo;

    @Resource
    CurrentUserInfo currentUserInfo;

    /**
     *  该注解在对象使用前执行，并且只执行一次
     */
    @PostConstruct
    public void setUserService(){
        staticCurrentUserInfo = currentUserInfo;
    }

    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //五分钟后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }
    public static UserDTO getCurrentUser(){
        try{
            UserDTO userDTO = new UserDTO();
            userDTO.setToken(staticCurrentUserInfo.getCurrentToken());
            userDTO.setMenus(staticCurrentUserInfo.getCurrentMenus());
            BeanUtil.copyProperties(staticCurrentUserInfo.getUser(), userDTO, true);
            return userDTO;
        }catch (Exception e){
            throw new ServiceException("500",e.getMessage());
        }
    }

}
