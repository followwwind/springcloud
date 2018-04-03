package com.wind.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
}
