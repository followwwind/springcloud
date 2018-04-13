package com.wind.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @RequestMapping("/hi")
    public String home() {
        return "hi ,i'm miya";
    }
}
