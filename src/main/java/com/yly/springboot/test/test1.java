package com.yly.springboot.test;

import com.yly.springboot.cron.CronTest1;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class test1 {

    @Resource
    CronTest1 CronTest1;

    @Bean(name = {"t1"})
    public int t1(){
        return 123;
    }

}
