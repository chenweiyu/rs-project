package com.xhm.rs.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "ontrial")
public class Ontrial {
    private Long id;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate beginTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endTime;
    Long staff_id;
    @TableField(exist = false)
	private Long staffName;
    @TableField(exist = false)
	private String positionName;
    @TableField(exist = false)
	private String sectionName;


    public Ontrial(){

    }

    public Ontrial(String status, LocalDate beginTime, LocalDate endTime, Long staff_id){
        this.status = status;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.staff_id = staff_id;
    }

}
