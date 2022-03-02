package com.xhm.rs.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhm.rs.entity.Section;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * BaseMapper
 * 
 */
public interface SectionDao extends BaseMapper<Section>{
    public  List<Map<String, Object>> getRsMonthListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getycrsListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getymrsListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getxrzListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getlzListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getdrListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getdcListPage(@Param("map") Map<String, Object> params);
    public  List<Map<String, Object>> getRsList();
}
