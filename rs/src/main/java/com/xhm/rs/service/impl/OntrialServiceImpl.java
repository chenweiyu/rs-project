package com.xhm.rs.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.common.core.P;
import com.xhm.rs.dao.OntrialDao;
import com.xhm.rs.entity.Ontrial;
import com.xhm.rs.service.OntrialService;

import org.springframework.stereotype.Service;

@Service
public class OntrialServiceImpl extends ServiceImpl<OntrialDao, Ontrial> implements OntrialService{

    @Override
    public IPage<Ontrial> pageAll(Map<String, Object> params) {
        IPage<Ontrial> result= this.baseMapper.pageAll(P.page(params), params);    
        return result;
    }

    

}
