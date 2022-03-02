package com.xhm.rs.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.P;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.Section;
import com.xhm.rs.service.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping("getNumEcharts")
    public R getNumEcharts(@RequestParam Map<String, Object> params) {
        List<Map<String, Object>> map;
        map = sectionService.getRsList();
        return R.ok(map);
    }

    @PostMapping("querylist")
    public R querylist(@RequestParam Map<String, Object> params) {
        Map<String, Object> params1 = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> list;
        List<Map<String, Object>> list1;
        List<Map<String, Object>> list2;
        List<Map<String, Object>> list3;
        List<Map<String, Object>> list4;
        List<Map<String, Object>> list5;
        List<Map<String, Object>> list6;
        String OntrialBeginTime = (String) params.get("OntrialBeginTime");
        if (OntrialBeginTime == "") {
            Calendar cale = Calendar.getInstance();
            int year = cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            String begin = year + "-" + month + "-1";
            String end = year + "-" + (month + 1) + "-1";
            params.put("OntrialBeginTime", begin);
            params.put("OntrialEndTime", end);
            String yc = year + "-" + month + "-10";
            String ym = year + "-" + month + "-20";
            params1.put("OntrialBeginTime", begin);
            params1.put("yc", yc);
            params2.put("ym", ym);
            params2.put("OntrialEndTime", end);
        }
        list = sectionService.getRsMonthListPage(params);
        list1 = sectionService.getycrsListPage(params1);
        list2 = sectionService.getymrsListPage(params2);
        list3 = sectionService.getxrzListPage(params);
        list4 = sectionService.getlzListPage(params);
        list5 = sectionService.getdrListPage(params);
        list6 = sectionService.getdcListPage(params);
        Map<String, Object> map = new HashMap<>();
        map.put("k", list);
        map.put("k1", list1);
        map.put("k2", list2);
        map.put("k3", list3);
        map.put("k4", list4);
        map.put("k5", list5);
        map.put("k6", list6);
        return R.ok(map);
    }

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        // QueryWrapper
        IPage<Section> list;
        String query = (String) params.get("query");
        if (params.size() == 0) {
            List<Section> listSection = sectionService.list();
            return R.ok(listSection);
        }
        if (query != "") {
            QueryWrapper<Section> wrapper = new QueryWrapper<>();
            wrapper.like("section_name", query).or().like("section_num", query);
            list = sectionService.page(P.page(params), wrapper);
        } else {
            list = sectionService.page(P.page(params), new QueryWrapper<Section>().orderByDesc("commit_time"));
        }
        // int a=4/0;
        return R.ok(list);
    }

    /**
     * get
     * 
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        Section section = sectionService.getById(id);
        return R.ok(section);
    }

    /**
     * save
     * 
     * @Validated 配合后端验证
     * 
     * 
     * @param blog
     * @return
     */
    @PostMapping("/save")
    public R save(@Validated @RequestBody Section section) {
        section.setCommitTime(LocalDateTime.now());
        // int a=4/0;
        sectionService.save(section);
        return R.ok();
    }

    /**
     * 
     * 
     * @param blog
     * @return
     */
    @PostMapping("/update")
    public R update(@Validated @RequestBody Section section) {
        sectionService.updateById(section);
        return R.ok();
    }

    /**
     * 
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        System.out.println(ids);
        sectionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}
