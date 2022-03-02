package com.xhm.rs.controller;

import java.util.Calendar;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.TransferSection;
import com.xhm.rs.service.TransferSectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RestController
@RequestMapping("/transferSection")
public class TransferSectionController {

    @Autowired
    TransferSectionService transferSectionService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        IPage<TransferSection> list;
        list = transferSectionService.pageAllTransfer(params);
        return R.ok(list);
    }


    @PostMapping("/save")
    public R save(@Validated @RequestBody TransferSection transferSection){      
        transferSectionService.saveTransfer(transferSection);
        return R.ok();
    }

    @PostMapping("/querylist")
    public R querylist(@RequestParam Map<String, Object> params) {
        IPage<Map<String, Object>> list;
        String OntrialBeginTime = (String) params.get("OntrialBeginTime");
        if (OntrialBeginTime == ""){
            Calendar cale = Calendar.getInstance();
            int year = cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            String begin = year + "-" + month + "-1";
            String end = year + "-" + (month + 1) + "-1";
            params.put("OntrialBeginTime", begin);
            params.put("OntrialEndTime", end);
        }
        list = transferSectionService.getTransferSectionPage(params);
        return R.ok(list);
    }

}
