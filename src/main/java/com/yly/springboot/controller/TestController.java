package com.yly.springboot.controller;

import com.yly.springboot.Utils.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ttt")
public class TestController {
    @Autowired
    A a;

    @GetMapping
    public String  ttt(){
        return a.getPort();
    }
}
