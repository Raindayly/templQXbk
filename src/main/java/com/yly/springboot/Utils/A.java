package com.yly.springboot.Utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Value("${server.port}")
    String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
