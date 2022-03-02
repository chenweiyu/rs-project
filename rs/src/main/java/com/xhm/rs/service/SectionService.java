package com.xhm.rs.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.Section;

/**
 * 
 * IService
 * 
 */
public interface SectionService extends IService<Section> {
    public List<Map<String, Object>> getRsMonthListPage(Map<String, Object> params);
    public List<Map<String, Object>> getycrsListPage(Map<String, Object> params);
    public List<Map<String, Object>> getymrsListPage(Map<String, Object> params);
    public List<Map<String, Object>> getxrzListPage(Map<String, Object> params);
    public List<Map<String, Object>> getlzListPage(Map<String, Object> params);
    public List<Map<String, Object>> getdrListPage(Map<String, Object> params);
    public List<Map<String, Object>> getdcListPage(Map<String, Object> params);
    public List<Map<String, Object>> getRsList();
}
