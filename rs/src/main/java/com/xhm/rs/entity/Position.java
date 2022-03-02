package com.xhm.rs.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "position")
public class Position {
    Long id;
    String positionName;
    String positionNum;
    String positionType;
    String positionDescribe;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
}
