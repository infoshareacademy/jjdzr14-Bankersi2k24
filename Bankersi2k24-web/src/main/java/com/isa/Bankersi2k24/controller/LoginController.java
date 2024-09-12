package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.User;
import com.isa.Bankersi2k24.services.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;


@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(User user, Model model) {
        model.addAttribute("content", "loginForm");
        model.addAttribute("user", user);
        return "main";
    }

    @PostMapping("/login")
    public String loginUser(@Valid User user, BindingResult bindingResult, ModelMap model,
                            @ModelAttribute("login") String login,
                            @ModelAttribute("password") String password,
                            final RedirectAttributes redirectAttributes){
        try{
            if(bindingResult.hasErrors()){
                String errMessage="";
                for (ObjectError objectError:bindingResult.getAllErrors()) {
                    errMessage +=objectError.getDefaultMessage()+"  ";

                }

                model.addAttribute("content", "loginForm");
                model.addAttribute("errorMsg", errMessage);
                return "main";
            }
            boolean aa = model.containsAttribute("login");

            if(userService.loginUser(login, password)){
                model.addAttribute("content", "dashboard")
                        .addAttribute("userId",userService.findUserByLogin(login).getId());
                redirectAttributes.addFlashAttribute("userId", userService.findUserByLogin(login).getId());
                return "redirect:/dashboard";
            }else
            {
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
    @GetMapping("/user/{id}")
    public String getUserDetailsByUserId(@PathVariable BigInteger id,
                                         Model model) {
//        UserService userService = null;
        try {
            User user = userService.getUserById(id);
            model.addAttribute("id", id);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("id", id);
            model.addAttribute("user", null);
        }
        return "userDetails";
    }
}

