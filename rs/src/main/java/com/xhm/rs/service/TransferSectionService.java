package com.xhm.rs.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.TransferSection;

public interface TransferSectionService extends IService<TransferSection> {
    public IPage<TransferSection> pageAllTransfer(Map<String, Object> params);
    public void saveTransfer(TransferSection transferSection);

    public IPage<Map<String, Object>> getTransferSectionPage(Map<String, Object> params);
}
