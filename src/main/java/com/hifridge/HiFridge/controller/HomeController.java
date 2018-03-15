package com.hifridge.HiFridge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping("/hello")
    public String hello() {
        return "Hello. All your base are belong to us.";
    }

    @RequestMapping("/waterpolo")
    public String waterpolo() {
        return "Tin Alex.";
    }

}
