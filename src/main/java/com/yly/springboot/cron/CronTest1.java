package com.yly.springboot.cron;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CronTest1 {
    int i = 0;
//    @Resource


//    @Scheduled(cron = "* * * * * *")
    private void initRequest(){
        System.out.println("cron"+i++);
    }

    @Bean(name = {"ab"})
    public void abc(){
    }
}
