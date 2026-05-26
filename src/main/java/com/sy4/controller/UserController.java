package com.sy4.controller;

import com.sy4.entity.Device;
import com.sy4.entity.User;
import com.sy4.service.DeviceService;
import com.sy4.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/toLogin")

    public String toLogin() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String loginName, String password,
                        HttpSession session, Model model) {
        User user = userService.login(loginName, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            if (user.getType() == 0) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/user/home";
            }
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "user/login";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "user/register";
    }


    @PostMapping("/register")
    public String register(User user, HttpSession session) {
        user.setType(1);
        userService.register(user);
        session.setAttribute("loginUser", user);
        return "redirect:/user/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/user/toLogin";
        List<Device> devices = deviceService.findByUserId(loginUser.getId());
        model.addAttribute("devices", devices);
        session.setAttribute("loginUser", userService.findById(loginUser.getId()));
        return "user/home";
    }

    @GetMapping("/toEditProfile")
    public String toEditProfile() {
        return "user/editProfile";
    }

    @PostMapping("/editProfile")
    public String editProfile(HttpSession session, String name, String phone, String email) {
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setName(name);
        loginUser.setPhone(phone);
        loginUser.setEmail(email);
        userService.updateProfile(loginUser);
        session.setAttribute("loginUser", loginUser);
        return "redirect:/user/home";
    }

    @GetMapping("/toChangePassword")
    public String toChangePassword() {
        return "user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(HttpSession session, String oldPassword,
                                  String newPassword, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!loginUser.getPassword().equals(oldPassword)) {
            model.addAttribute("msg", "原密码错误");
            return "user/changePassword";
        }
        userService.changePassword(loginUser.getId(), newPassword);
        loginUser.setPassword(newPassword);
        session.setAttribute("loginUser", loginUser);
        model.addAttribute("msg", "密码修改成功");
        return "user/changePassword";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/toLogin";
    }
}
