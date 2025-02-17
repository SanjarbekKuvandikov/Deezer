package com.example.deezer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/songsList")
    public String home() {
        return "home";
    }

    @RequestMapping("allList")
    public String all() {
        return "all";
    }

    @GetMapping("no_url")
    public String noUrl() {
        return "no_url";
    }
}
