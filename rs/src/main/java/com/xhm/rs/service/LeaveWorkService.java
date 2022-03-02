package com.xhm.rs.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.LeaveWork;

public interface LeaveWorkService extends IService<LeaveWork> {
    public IPage<LeaveWork> pageAllLeave(Map<String, Object> params);

    public IPage<Map<String, Object>> getLeaveStaffListPage(Map<String, Object> params);
}
