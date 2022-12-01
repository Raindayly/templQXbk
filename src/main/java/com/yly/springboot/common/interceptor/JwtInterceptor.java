package com.yly.springboot.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.yly.springboot.common.Result_Code;
import com.yly.springboot.entity.User;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {


        if( !(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader("token");
        //如果请求的不是一个具体的方法就直接跳过token验证

        if(token == null){
            throw new ServiceException(Result_Code.CODE_620.getCode(),Result_Code.CODE_620.getMsg());
        }
        String userId;
        try{
            DecodedJWT decode = JWT.decode(token);
            List<String> audience = decode.getAudience();

            userId = audience.get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException(Result_Code.CODE_620.getCode(),Result_Code.CODE_620.getMsg());
        }
        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(Result_Code.CODE_602.getCode(),Result_Code.CODE_602.getMsg());
        }
        //用户密码加签验证 token
        Verification require = JWT.require(Algorithm.HMAC256(user.getPassword()));
        JWTVerifier build = require.build();
        try{
            build.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException(Result_Code.CODE_620.getCode(),Result_Code.CODE_620.getMsg());
        }
        return true;
    }
}
