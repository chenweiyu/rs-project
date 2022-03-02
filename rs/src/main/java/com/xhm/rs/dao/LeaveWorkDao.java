package com.xhm.rs.dao;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.entity.LeaveWork;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * BaseMapper
 * 
 */
public interface LeaveWorkDao extends BaseMapper<LeaveWork> {
    public IPage<LeaveWork> pageAllLeave(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

    public IPage<Map<String, Object>> getLeaveStaffListPage(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);
}
