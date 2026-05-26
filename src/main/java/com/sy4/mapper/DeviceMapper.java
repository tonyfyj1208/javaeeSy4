package com.sy4.mapper;

import com.sy4.entity.Device;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface DeviceMapper {
    List<Device> findByUserId(Integer userId);
    List<Device> findAll();
    Device findById(Integer id);
    void insert(Device device);
    void update(Device device);
    void updateUserId(Device device);
    void deleteById(Integer id);
    List<Device> search(@Param("name") String name, @Param("loginName") String loginName, @Param("userName") String userName);
}
