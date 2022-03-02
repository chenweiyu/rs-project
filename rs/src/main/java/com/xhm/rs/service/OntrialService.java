package com.xhm.rs.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.Ontrial;

/**
 * 
 * IService
 * 
 */
public interface OntrialService extends IService<Ontrial> {

    public IPage<Ontrial> pageAll(Map<String, Object> params);

}
