package com.xhm.rs.dao;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.entity.TransferSection;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * BaseMapper
 * 
 */
public interface TransferSectionDao extends BaseMapper<TransferSection> {
    public IPage<TransferSection> pageAllTransfer(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

    public IPage<Map<String, Object>> getTransferSectionPage(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);
}
