package com.sy4.mapper;

import com.sy4.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    User findByLoginName(String loginName);
    User findById(Integer id);
    void insert(User user);
    void update(User user);
    void updatePassword(User user);
    void deleteById(Integer id);
    List<User> findAll();
    List<User> search(@Param("name") String name, @Param("phone") String phone, @Param("type") Integer type);
}
