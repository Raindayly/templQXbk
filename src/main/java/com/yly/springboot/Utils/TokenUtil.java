package com.yly.springboot.Utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yly.springboot.entity.UserDTO;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.service.CurrentUserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

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
