package com.sy4.service;

import com.sy4.entity.User;
import com.sy4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String loginName, String password) {
        User user = userMapper.findByLoginName(loginName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void register(User user) {
        userMapper.insert(user);
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public void updateProfile(User user) {
        userMapper.update(user);
    }

    public void changePassword(Integer id, String newPassword) {
        User user = new User();
        user.setId(id);
        user.setPassword(newPassword);
        userMapper.updatePassword(user);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> search(String name, String phone, Integer type) {
        return userMapper.search(name, phone, type);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
}
