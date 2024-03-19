package com.mail.controllers;

import com.mail.models.User;
import com.mail.models.dto.UserCreateDto;
import com.mail.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        List<User> users = userService.getAllUsers();
        modelAndView.setViewName(users.isEmpty() ? "empty" : "get_users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, ModelMap modelMap) { // аннотация если мы хотим достать что-то из url
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            modelMap.addAttribute("user", user.get());
            return "get_user_by_id";
        }
        return "empty";
    }

    @PostMapping("/{id}")
    public String deleteByUserId(@PathVariable("id") Long id, ModelMap modelMap) {
        return userService.deleteUserById(id) ? "success" : "failure";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Valid UserCreateDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()){
                System.out.println(error);
            }
            return "failure";
        }
        return userService.createUser(user) ? "success" : "failure";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("username") String username,
                             @RequestParam("isDeleted") Boolean isDeleted,
                             @RequestParam("id") Long id,
                             @RequestParam("userLocation") String userLocation) {
        return userService.updateUser(id, username, isDeleted, userLocation) ? "success" : "failure";
    }
}
