package com.xhm.rs.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.Staff;

/**
 * 
 * IService
 * 
 */
public interface StaffInfoService extends IService<Staff> {
    public IPage<Map<String, Object>> getListPage(Map<String, Object> params);

    public IPage<Map<String, Object>> getNewStaffListPage(Map<String, Object> params);

    public List<Map<String, Object>> getXlList();
}
