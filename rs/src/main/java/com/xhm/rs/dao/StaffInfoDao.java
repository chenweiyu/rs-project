package com.xhm.rs.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.entity.Staff;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * BaseMapper
 * 
 */
public interface StaffInfoDao extends BaseMapper<Staff>{
    public IPage<Map<String, Object>> getListPage(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

    public IPage<Map<String, Object>> getNewStaffListPage(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

    public List<Map<String, Object>> getXlList();
}
