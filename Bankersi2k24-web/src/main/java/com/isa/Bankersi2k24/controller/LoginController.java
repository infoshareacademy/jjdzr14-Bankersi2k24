package com.isa.Bankersi2k24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
//    @GetMapping("/")
@GetMapping()
    public String viewHomePage() {
        return "menu";
    }

}