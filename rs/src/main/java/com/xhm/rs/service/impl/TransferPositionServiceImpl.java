package com.xhm.rs.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.common.core.P;
import com.xhm.rs.dao.TransferPositionDao;
import com.xhm.rs.entity.Position;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.entity.TransferPosition;
import com.xhm.rs.service.PositionService;
import com.xhm.rs.service.StaffService;
import com.xhm.rs.service.TransferPositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferPositionServiceImpl extends ServiceImpl<TransferPositionDao, TransferPosition> implements TransferPositionService{

    @Autowired
    StaffService staffService;
    @Autowired
    PositionService positionService;

    @Override
    public IPage<TransferPosition> pageAllTransfer(Map<String, Object> params) {
        IPage<TransferPosition> result= this.baseMapper.pageAllTransfer(P.page(params), params);    
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTransfer(TransferPosition transferPosition){
        TransferPosition transferPosition2 = this.getOne(new QueryWrapper<TransferPosition>().eq("staff_id", transferPosition.getStaff_id()));
        if (transferPosition2 != null){
            this.removeById(transferPosition2.getId());
        }
        this.save(transferPosition);
        Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_id", transferPosition.getStaff_id()).last("LIMIT 1"));
        Position position = positionService.getOne(new QueryWrapper<Position>().eq("position_name", transferPosition.getPosition_after()).last("LIMIT 1"));
        staff.setPositionId(position.getId());
        staffService.updateById(staff);
    }

}
