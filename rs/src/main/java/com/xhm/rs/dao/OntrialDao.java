package com.xhm.rs.dao;


import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.entity.Ontrial;

import org.apache.ibatis.annotations.Param;

public interface OntrialDao extends BaseMapper<Ontrial>{
    public IPage<Ontrial> pageAll(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);
}
