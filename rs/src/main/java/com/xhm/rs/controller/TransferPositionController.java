package com.xhm.rs.controller;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.TransferPosition;
import com.xhm.rs.service.TransferPositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/transferPosition")
public class TransferPositionController {

    @Autowired
    TransferPositionService transferPositionService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        IPage<TransferPosition> list;
         
        list = transferPositionService.pageAllTransfer(params);
        return R.ok(list);
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/save")
    public R save(@Validated @RequestBody TransferPosition transferPosition){ 
        log.info("msg--"+transferPosition.toString());     
        transferPositionService.saveTransfer(transferPosition);
        return R.ok();
    }

}
