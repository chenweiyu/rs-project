package com.xhm.rs.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "section")
public class Section {
    Long id;
    String section_name;
    String sectionNum;
    String sectionPlace;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commitTime;
    public Section(Long id, String section_name){
        this.id = id;
        this.section_name = section_name;
    }
    public void sectionTime(LocalDateTime commitTime) {
        this.commitTime = commitTime;
    }
}
