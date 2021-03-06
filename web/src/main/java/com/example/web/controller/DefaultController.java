package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String home() {
        return "redirect:/minimal";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
