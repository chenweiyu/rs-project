package com.xhm.rs.dao;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.entity.TransferPosition;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * BaseMapper
 * 
 */
public interface TransferPositionDao extends BaseMapper<TransferPosition> {
    public IPage<TransferPosition> pageAllTransfer(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);
}
