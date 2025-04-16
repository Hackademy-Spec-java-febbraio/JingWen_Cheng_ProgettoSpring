package it.aulab.xjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import it.aulab.xjava.dtos.UserDto;
import it.aulab.xjava.models.User;
import it.aulab.xjava.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 首页
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 显示注册页面
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    // 显示登录页面
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // 提交注册表单
    @PostMapping("/register/save")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 检查邮箱是否已被注册
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", "null", "There is already an account registered with that email");
        }

        // 如果有验证错误，返回注册页并回显错误信息
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "auth/register";
        }

        // 保存新用户
        userService.saveUser(userDto, redirectAttributes, request, response);

        // 使用 Flash Attribute 显示成功消息
        redirectAttributes.addFlashAttribute("success", "Account created successfully!");

        // 重定向回注册页或登录页
        return "redirect:/register";
    }
}
