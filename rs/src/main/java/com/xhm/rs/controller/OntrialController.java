package com.xhm.rs.controller;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.Ontrial;
import com.xhm.rs.service.OntrialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ontrial")
public class OntrialController {
    
    @Autowired
    OntrialService ontrialService;

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        IPage<Ontrial> list;
        //String query = (String) params.get("query");
        // if (query != "") {
        //     QueryWrapper<Position> wrapper = new QueryWrapper<>();
        //     wrapper.like("position_name", query).or().like("position_num", query).or().like("position_describe", query);
        //     list = ontrialService.page(P.page(params), wrapper);
        // }else{
            list = ontrialService.pageAll(params);
            // SELECT s.staff_id, s.staff_name, p.position_name, se.section_name, o.status, o.begin_time, o.end_time FROM staff s, ontrial o, position p, section se where s.position_id = p.id and s.section_id = se.id and o.staff_id = s.staff_id
        //}
        return R.ok(list);
    }

    @PostMapping("/onWork/{staff_id}")
    public R onWork(@PathVariable("staff_id") Long staff_id){

        Ontrial ontrial = ontrialService.getOne(new QueryWrapper<Ontrial>().eq("staff_id", staff_id).last("LIMIT 1"));
        log.info(" msg " + ontrial.toString());
        ontrial.setStatus("正常");
        ontrialService.updateById(ontrial);
        return R.ok();
    }

    @PostMapping("/onDelay/{staff_id}")
    public R onDelay(@PathVariable("staff_id") Long staff_id){
        Ontrial ontrial = ontrialService.getOne(new QueryWrapper<Ontrial>().eq("staff_id", staff_id).last("LIMIT 1"));
        ontrial.setStatus("延期");
        ontrialService.updateById(ontrial);
        return R.ok();
    }

    @PostMapping("/noEmployment/{staff_id}")
    public R noEmployment(@PathVariable("staff_id") Long staff_id){
        Ontrial ontrial = ontrialService.getOne(new QueryWrapper<Ontrial>().eq("staff_id", staff_id).last("LIMIT 1"));
        ontrial.setStatus("不录用");
        ontrialService.updateById(ontrial);
        return R.ok();
    }

    // @GetMapping("/info/{id}")
    // public R info(@PathVariable("id") Long id){
	// 	Position position = positionService.getById(id);
    //     return R.ok(position);
    // }

    // @PostMapping("/save")
    // public R save(@Validated @RequestBody Position position){      
    //     position.setUpdateTime(LocalDateTime.now());
	// 	positionService.save(position);
    //     return R.ok();
    // }

    // @PostMapping("/update")
    // public R update(@Validated @RequestBody Position position){
	// 	positionService.updateById(position);
    //     return R.ok();
    // }
    
    // @PostMapping("/delete")
    // public R delete(@RequestBody Long[] ids){
	// 	positionService.removeByIds(Arrays.asList(ids));
    //     return R.ok();
    // }
}
