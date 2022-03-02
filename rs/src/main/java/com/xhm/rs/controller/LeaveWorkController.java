package com.xhm.rs.controller;

import java.util.Calendar;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.P;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.LeaveWork;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.service.LeaveWorkService;
import com.xhm.rs.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/leaveWork")
public class LeaveWorkController {

    @Autowired
    LeaveWorkService leaveWorkService;
    @Autowired
    StaffService staffService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        IPage<LeaveWork> list;
        list = leaveWorkService.page(P.page(params));
        return R.ok(list);
    }


    @PostMapping("/save")
    public R save(@Validated @RequestBody LeaveWork leaveWork){
        log.info(" msg " + leaveWork.toString());
        leaveWorkService.save(leaveWork);
        Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_id", leaveWork.getStaffId()));
        staffService.removeById(staff.getId());
        return R.ok();
    }

    @PostMapping("/listLeaveWork")
    public R newList(@RequestParam Map<String, Object> params) {
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
        list = leaveWorkService.getLeaveStaffListPage(params);
        return R.ok(list);
    }

}
