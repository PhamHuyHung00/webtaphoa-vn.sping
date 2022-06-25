package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    MessageSource messageSource;


    @GetMapping()
    public String getAllUser(Model model) {
        model.addAttribute("user", userServiceImpl.getAllUser());
        return "user-ad";
    }


    @GetMapping("/register-form")
    public String registerForm(Model model) {
        model.addAttribute("register", new User());
        return "register-form";
    }


    @PostMapping("/register-form")
    public String saveUser(@Valid @ModelAttribute("register") User user, BindingResult result, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        if (result.hasErrors()) {
            return "register-form";
        }
        if (userRepository.getCountLogin(user.getLogin()) == 1) {
            model.addAttribute("error", "Account already exists");
            return "register-form";
        }

            userService.save(user);
            return "redirect:/user/login-form";

    }


    @GetMapping("/login-form")
    public String loginForm() {
//        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/log-out")
    public String logout() {
        return "redirect:/user/login-form";
    }


//    @PostMapping("/login-form")
//    public String login(@ModelAttribute("user") User user) {
//        User oauthUser = userServiceImpl.findByLoginAndPassword(user.getLogin(), user.getPassword());
//        if (Objects.nonNull(oauthUser)) {
//            return "redirect:/home";
//        } else {
//            return "redirect:/login-form";
//        }
//    }


}
