package com.xhm.rs.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.common.core.P;
import com.xhm.rs.dao.LeaveWorkDao;
import com.xhm.rs.entity.LeaveWork;
import com.xhm.rs.service.LeaveWorkService;
import com.xhm.rs.service.SectionService;
import com.xhm.rs.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveWorkServiceImpl extends ServiceImpl<LeaveWorkDao, LeaveWork> implements LeaveWorkService{

    @Autowired
    StaffService staffService;
    @Autowired
    SectionService sectionService;

    @Override
    public IPage<LeaveWork> pageAllLeave(Map<String, Object> params) {
        IPage<LeaveWork> result= this.baseMapper.pageAllLeave(P.page(params), params);    
        return result;
    }

    @Override
    public IPage<Map<String, Object>> getLeaveStaffListPage(Map<String, Object> params) {
        IPage<Map<String, Object>> result= this.baseMapper.getLeaveStaffListPage(P.page(params), params);    
        return result;
    }

}
