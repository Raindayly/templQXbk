package com.yly.springboot.controller.test;

import com.yly.springboot.test.test1;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConTest1 {

    int a;
    @Resource
    test1 t;
    @Resource
    ApplicationContext run;

    @GetMapping("/haha")
    public int testRun(){
        Class<Integer> a = int.class;
        return run.getBean("t1",a);
    }
}
