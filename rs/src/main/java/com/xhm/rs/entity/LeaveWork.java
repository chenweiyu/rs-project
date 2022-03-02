package com.xhm.rs.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "leave_staff")
public class LeaveWork {
    Long id;
    Long staffId;
    String leaveType;
    String leaveGo;
    String isInto;
    String remark;
    String staffName;
    String sectionName;
    String positionName;

    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate leaveTime;

    public LeaveWork(){
        
    }
    
}
