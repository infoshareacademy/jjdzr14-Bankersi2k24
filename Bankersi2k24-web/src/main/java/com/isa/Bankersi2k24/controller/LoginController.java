package com.isa.Bankersi2k24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    public String viewHomePage() {
        return "index";
    }

}