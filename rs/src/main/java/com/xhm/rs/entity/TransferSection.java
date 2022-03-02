package com.xhm.rs.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "transfer_section")
public class TransferSection {
    Long id;
    String sectionAfter;
    String section_name;
    String reason;
    String remark;

    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate beginTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate endTime;

    Long staff_id;
    

    public TransferSection(String sectionAfter, String section_name, String reason, String remark,
            LocalDate beginTime, LocalDate endTime, Long staff_id) {
                this.sectionAfter = sectionAfter;
                this.section_name = section_name;
                this.reason = reason;
                this.remark = remark;
                this.beginTime = beginTime;
                this.endTime = endTime;
                this.staff_id = staff_id;
    }
    public TransferSection(){
        
    }
}
