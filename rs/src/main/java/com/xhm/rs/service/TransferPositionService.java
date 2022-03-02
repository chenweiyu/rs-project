package com.xhm.rs.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.TransferPosition;

public interface TransferPositionService extends IService<TransferPosition> {
    public IPage<TransferPosition> pageAllTransfer(Map<String, Object> params);
    public void saveTransfer(TransferPosition transferPosition);
}
