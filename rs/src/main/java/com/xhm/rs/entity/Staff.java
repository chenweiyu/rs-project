package com.xhm.rs.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "staff")
public class Staff {

    Long id;
    Long staffId;
    String staffName;
    String sex;
    Long positionId;
    String staffPhone;
    Long sectionId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate intoTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;
    String identity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate attendTime;
    String workForm;
    String peopleSource;
    String politicalOutlook;
    String nation;
    String nativePlace;
    String email;
    Double height;
    String bloodType;
    String maritalStatus;
    String birthPlace;
    String registeredResidence;
    String highestEducation;
    String highestDegree;
    String graduateSchool;
    String major;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate graduateTime;
    String isOntrial;
    String avatar;

    @TableField(exist = false)
    String positionName;
    @TableField(exist = false)
    String sectionName;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate OntrialBeginTime;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate OntrialEndTime;

    public void getSection_id(Long id2) {
        this.sectionId = id2;
    }

    public Staff(Long staff_id2, String staff_name2, String sex2, Long position_id2, String staff_phone2,
            Long section_id2) {
        staffId = staff_id2;
        staffName = staff_name2;
        sex = sex2;
        positionId = position_id2;
        staffPhone = staff_phone2;
        sectionId = section_id2;

    }

    public Staff(Long id2, Long staff_id2, String staff_name2, String sex2, Long position_id2, String staff_phone2,
            Long section_id2) {
        id = id2;
        staffId = staff_id2;
        staffName = staff_name2;
        sex = sex2;
        positionId = position_id2;
        staffPhone = staff_phone2;
        sectionId = section_id2;

    }

    // public Staff(Long id, Long staff_id, String staff_name, String sex, Long position_id, String staff_phone,
    //         Long section_id, LocalDate into_time, LocalDate birthday, String identity, LocalDate attend_time,
    //         String work_form, String people_source, String political_outlook, String nation, String native_place,
    //         String email, Double height, String blood_type, String marital_status, String birth_place,
    //         String registered_residence, String highest_education, String highest_degree, String graduate_school,
    //         String major, LocalDate graduate_time) {

    // }
    public Staff(){

    }
    
}