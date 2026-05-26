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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    // ========== Home ==========

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null || loginUser.getType() != 0) return "redirect:/user/toLogin";
        List<Device> devices = deviceService.findAll();
        List<User> users = userService.findAll();
        model.addAttribute("devices", devices);
        model.addAttribute("users", users);
        return "admin/home";
    }

    // ========== 用户管理 ==========

    @GetMapping("/users")
    public String listUsers(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String phone,
                            @RequestParam(required = false) Integer type,
                            Model model) {
        List<User> users = userService.search(name, phone, type);
        model.addAttribute("users", users);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("type", type);
        return "admin/users";
    }

    @GetMapping("/user/toAdd")
    public String toAddUser() {
        return "admin/userForm";
    }

    @PostMapping("/user/add")
    public String addUser(User user) {
        userService.register(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/toEdit")
    public String toEditUser(Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/userForm";
    }

    @PostMapping("/user/edit")
    public String editUser(User user) {
        userService.updateProfile(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete")
    public String deleteUser(Integer id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    // ========== 设备管理 ==========

    @GetMapping("/devices")
    public String listDevices(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String loginName,
                              @RequestParam(required = false) String userName,
                              Model model) {
        List<Device> devices = deviceService.search(name, loginName, userName);
        List<User> allUsers = userService.findAll();
        model.addAttribute("devices", devices);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("name", name);
        model.addAttribute("loginName", loginName);
        model.addAttribute("userName", userName);
        return "admin/devices";
    }

    @GetMapping("/device/toAdd")
    public String toAddDevice(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "admin/deviceForm";
    }

    @PostMapping("/device/add")
    public String addDevice(Device device) {
        deviceService.add(device);
        return "redirect:/admin/devices";
    }

    @GetMapping("/device/toEdit")
    public String toEditDevice(Integer id, Model model) {
        Device device = deviceService.findById(id);
        List<User> allUsers = userService.findAll();
        model.addAttribute("device", device);
        model.addAttribute("allUsers", allUsers);
        return "admin/deviceForm";
    }

    @PostMapping("/device/edit")
    public String editDevice(Device device) {
        deviceService.update(device);
        return "redirect:/admin/devices";
    }

    @GetMapping("/device/delete")
    public String deleteDevice(Integer id) {
        deviceService.deleteById(id);
        return "redirect:/admin/devices";
    }

    @GetMapping("/device/assign")
    public String toAssignDevice(Model model) {
        List<Device> devices = deviceService.findAll();
        List<User> allUsers = userService.findAll();
        model.addAttribute("devices", devices);
        model.addAttribute("allUsers", allUsers);
        return "admin/assignDevice";
    }

    @PostMapping("/device/assign")
    public String assignDevice(Integer deviceId, Integer userId) {
        deviceService.assignUser(deviceId, userId);
        return "redirect:/admin/devices";
    }

    @GetMapping("/device/recycle")
    public String recycleDevice(Integer id) {
        deviceService.recycle(id);
        return "redirect:/admin/devices";
    }
}
