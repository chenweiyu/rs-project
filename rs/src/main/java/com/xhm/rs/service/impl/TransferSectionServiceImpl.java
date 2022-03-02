package com.xhm.rs.service.impl;

import java.time.LocalDate;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.common.core.P;
import com.xhm.rs.dao.TransferSectionDao;
import com.xhm.rs.entity.Section;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.entity.TransferSection;
import com.xhm.rs.service.SectionService;
import com.xhm.rs.service.StaffService;
import com.xhm.rs.service.TransferSectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferSectionServiceImpl extends ServiceImpl<TransferSectionDao, TransferSection> implements TransferSectionService{

    @Autowired
    StaffService staffService;
    @Autowired
    SectionService sectionService;

    @Override
    public IPage<TransferSection> pageAllTransfer(Map<String, Object> params) {
        IPage<TransferSection> result= this.baseMapper.pageAllTransfer(P.page(params), params);    
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTransfer(TransferSection transferSection){
        TransferSection transferSection2 = this.getOne(new QueryWrapper<TransferSection>().eq("staff_id", transferSection.getStaff_id()));
        if (transferSection2 != null){
            this.removeById(transferSection2.getId());
        }

        String sectionAfter = transferSection.getSectionAfter();
        String section_name = transferSection.getSection_name();
        String reason = transferSection.getReason();
        String remark = transferSection.getRemark();
        LocalDate beginTime = transferSection.getBeginTime();
        LocalDate endTime = transferSection.getEndTime();
        Long staff_id = transferSection.getStaff_id();
        TransferSection transferSection1 = new TransferSection(sectionAfter, section_name, reason, remark, beginTime, endTime, staff_id);
        this.save(transferSection1);
        Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_id", staff_id).last("LIMIT 1"));
        Section section = sectionService.getOne(new QueryWrapper<Section>().eq("section_name", sectionAfter).last("LIMIT 1"));
        staff.getSection_id(section.getId());
        staffService.updateById(staff);
    }

    @Override
    public IPage<Map<String, Object>> getTransferSectionPage(Map<String, Object> params) {
        IPage<Map<String, Object>> result= this.baseMapper.getTransferSectionPage(P.page(params), params);    
        return result;
    }

}
