package com.xhm.rs.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "transfer_position")
public class TransferPosition {
    Long id;
    String position_after;
    String position_name;
    String transfer_type;
    String reason;
    String remark;
    

    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate transfer_time;

    Long staff_id;
   
}
