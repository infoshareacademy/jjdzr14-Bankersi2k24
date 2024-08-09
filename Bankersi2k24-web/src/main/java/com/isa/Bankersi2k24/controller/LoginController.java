package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.services.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("content", "loginForm");
        return "main";
    }

    @PostMapping("/login")
    public String loginUser(Model model,
                            @ModelAttribute("login") String login,
                            @ModelAttribute("password") String password){
        try{
            if(userService.loginUser(login, password)){
                model.addAttribute("content", "dashboard")
                        .addAttribute("userId",userService.findUserByLogin(login).getId());
                return "redirect:/dashboard";
            }else{
                model.addAttribute("content", "loginForm")
                        .addAttribute("errorMsg", "Login failed - check your credentials");
                return "main";
            }
        } catch (Exception e) {
            model.addAttribute("content", "loginForm")
                    .addAttribute("errorMsg", e.getMessage());
            return "main";
        }
    }

}

