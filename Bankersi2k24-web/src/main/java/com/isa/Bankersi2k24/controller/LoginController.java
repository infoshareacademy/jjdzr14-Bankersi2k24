package com.isa.Bankersi2k24.controller;

import com.isa.Bankersi2k24.models.User;
import com.isa.Bankersi2k24.models.UserDetailModel;
import com.isa.Bankersi2k24.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.util.Objects;


@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(User user, Model model) {
        model.addAttribute("content", "aboutUs");
        return "main";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    public static UserDetailModel getAuthorizedUserName(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return (UserDetailModel) principal;
        }
        return null;
    }

    @GetMapping("/web/auth")
    String authorize(HttpSession httpSession){
        String uname = Objects.requireNonNull(getAuthorizedUserName()).getUsername();
        if(uname != null){
            BigInteger id = userService.findUserByLogin(uname).getId();
            httpSession.setAttribute("useName", uname);
            httpSession.setAttribute("userId", id);
            return "redirect:/web/dashboard";
        }else
            return "/login?error";

    }

    @GetMapping("/user/{id}")
    public String getUserDetailsByUserId(@PathVariable BigInteger id,
                                         Model model) {
            User user = userService.getUserById(id);
             model.addAttribute("user", user);
        return "userDetails";
    }
    @GetMapping("/user/edit/{id}")
    public String editUserDetails(@PathVariable BigInteger id,
                                  Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUserDetails";
    }
    @PostMapping("/editUser")
    public String executeEditUserDetails(@Valid @ModelAttribute("user") User editedUser,
                                         BindingResult bindingResult,
                                         final RedirectAttributes redirectAttributes,
                                         Model model) {
        if (bindingResult.hasErrors()) {
            String errMessage = "";
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errMessage += objectError.getDefaultMessage() + "  ";

            }
            System.out.println(errMessage);
            redirectAttributes.addFlashAttribute("errMessage", errMessage);

            return "redirect:/user/" + editedUser.getId();
        }
        userService.editUser(editedUser);
        return "redirect:/user/" + editedUser.getId();
    }
}

