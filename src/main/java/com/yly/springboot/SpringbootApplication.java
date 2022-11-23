package com.yly.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SpringbootApplication {
    static ConfigurableApplicationContext run;
    public static void main(String[] args) {
        run = SpringApplication.run(SpringbootApplication.class, args);


    }
    @Bean(name = {"myrun"})
    public ConfigurableApplicationContext getRun(){
        return run;
    }

}
