package com.xhm.rs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.Staff;

/**
 * 
 * IService
 * 
 */
public interface StaffService extends IService<Staff> {
    public void addStaff(Staff staff);
}
