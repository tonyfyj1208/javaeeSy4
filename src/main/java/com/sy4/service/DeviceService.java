package com.sy4.service;

import com.sy4.entity.Device;
import com.sy4.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public List<Device> findByUserId(Integer userId) {
        return deviceMapper.findByUserId(userId);
    }

    public List<Device> findAll() {
        return deviceMapper.findAll();
    }

    public Device findById(Integer id) {
        return deviceMapper.findById(id);
    }

    public void add(Device device) {
        deviceMapper.insert(device);
    }

    public void update(Device device) {
        deviceMapper.update(device);
    }

    public void assignUser(Integer deviceId, Integer userId) {
        Device device = new Device();
        device.setId(deviceId);
        device.setUserId(userId);
        deviceMapper.updateUserId(device);
    }

    public void recycle(Integer deviceId) {
        Device device = new Device();
        device.setId(deviceId);
        device.setUserId(null);
        deviceMapper.updateUserId(device);
    }

    public void deleteById(Integer id) {
        deviceMapper.deleteById(id);
    }

    public List<Device> search(String name, String loginName, String userName) {
        return deviceMapper.search(name, loginName, userName);
    }
}
