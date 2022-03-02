package com.xhm.rs.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.P;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.Position;
import com.xhm.rs.service.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/position")
public class PositionController {
    
    @Autowired
    PositionService positionService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        IPage<Position> list;
        String query = (String) params.get("query");
        if (params.size() == 0) {
            List<Position> listPosition = positionService.list();
            log.info("--" + listPosition.toString());
            return R.ok(listPosition);
        }
        if (query != "") {
            QueryWrapper<Position> wrapper = new QueryWrapper<>();
            wrapper.like("position_name", query).or().like("position_num", query).or().like("position_describe", query);
            list = positionService.page(P.page(params), wrapper);
        }else{
            list = positionService.page(P.page(params), new QueryWrapper<Position>().orderByDesc("update_time"));
        }
        return R.ok(list);
    }

    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		Position position = positionService.getById(id);
        return R.ok(position);
    }

    @PostMapping("/save")
    public R save(@Validated @RequestBody Position position){      
        position.setUpdateTime(LocalDateTime.now());
		positionService.save(position);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@Validated @RequestBody Position position){
		positionService.updateById(position);
        return R.ok();
    }
    
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		positionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}
