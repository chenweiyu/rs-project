package com.xhm.rs.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.dao.SectionDao;
import com.xhm.rs.entity.Section;
import com.xhm.rs.service.SectionService;

import org.springframework.stereotype.Service;


/**
 * @Service
 * 
 * ServiceImpl
 * 
 */
@Service
public class SectionServiceImpl extends ServiceImpl<SectionDao, Section> implements SectionService{

    @Override
    public List<Map<String, Object>> getRsMonthListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getRsMonthListPage(params);    
        return result;
    }

    @Override
    public List<Map<String, Object>> getycrsListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getycrsListPage(params);    
        return result;
    }
    @Override
    public List<Map<String, Object>> getymrsListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getymrsListPage(params);    
        return result;
    }
    @Override
    public List<Map<String, Object>> getxrzListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getxrzListPage(params);    
        return result;
    }
    @Override
    public List<Map<String, Object>> getlzListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getlzListPage(params);    
        return result;
    }

    @Override
    public List<Map<String, Object>> getdrListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getdrListPage(params);    
        return result;
    }

    @Override
    public List<Map<String, Object>> getdcListPage(Map<String, Object> params) {
        List<Map<String, Object>> result= this.baseMapper.getdcListPage(params);    
        return result;
    }

    @Override
    public List<Map<String, Object>> getRsList() {
        List<Map<String, Object>> result= this.baseMapper.getRsList();    
        return result;
    }

}
