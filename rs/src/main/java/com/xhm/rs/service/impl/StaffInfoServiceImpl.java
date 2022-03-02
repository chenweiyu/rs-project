package com.xhm.rs.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.common.core.P;
import com.xhm.rs.dao.StaffInfoDao;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.service.StaffInfoService;

import org.springframework.stereotype.Service;


/**
 * @Service
 * 
 * ServiceImpl
 * 
 */
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoDao, Staff> implements StaffInfoService{
    @Override
    public IPage<Map<String, Object>> getListPage(Map<String, Object> params) {

        IPage<Map<String, Object>> result= this.baseMapper.getListPage(P.page(params), params);    

        return result;
    }

    @Override
    public IPage<Map<String, Object>> getNewStaffListPage(Map<String, Object> params) {
        IPage<Map<String, Object>> result= this.baseMapper.getNewStaffListPage(P.page(params), params);
        return result;
    }

    @Override
    public List<Map<String, Object>> getXlList() {
        List<Map<String, Object>> result= this.baseMapper.getXlList();
        return result;
    }
}
