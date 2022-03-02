package com.xhm.rs.service.impl;

import java.time.LocalDate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.dao.StaffDao;
import com.xhm.rs.entity.Ontrial;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.service.OntrialService;
import com.xhm.rs.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Service
 * 
 * ServiceImpl
 * 
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService{

    @Autowired
    OntrialService ontrialService;

    @Override
    public void addStaff(Staff staff) {
        this.save(staff);
        if (staff.getIsOntrial().equals("是")){
            String status = "正常";
            LocalDate beginTime = staff.getOntrialBeginTime();
            LocalDate endTime = staff.getOntrialEndTime();
            Long staff_id = staff.getStaffId();
            Ontrial ontrial = new Ontrial(status, beginTime, endTime, staff_id);
            ontrialService.save(ontrial);
        }
        
    }

}
