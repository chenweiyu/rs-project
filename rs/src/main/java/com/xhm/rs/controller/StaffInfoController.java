package com.xhm.rs.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.Position;
import com.xhm.rs.entity.Section;
import com.xhm.rs.entity.Staff;
import com.xhm.rs.service.PositionService;
import com.xhm.rs.service.SectionService;
import com.xhm.rs.service.StaffInfoService;
import com.xhm.rs.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/staffInfo")
public class StaffInfoController {

    @Autowired
    StaffInfoService staffInfoService;
    @Autowired
    PositionService positionService;
    @Autowired
    SectionService sectionService;
    @Autowired
    StaffService staffService;

    String url = "upload/avatar";
    Staff search_staff;

    @GetMapping("/xltj")
    public R xltj(){
        List<Map<String, Object>> map;
        map = staffInfoService.getXlList();
        return R.ok(map);
    }

    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        IPage<Map<String, Object>> list;
        // String query = (String) params.get("query");
        // if (query != "") {
        // // QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        // // QueryWrapper<Position> position_wrapper = new QueryWrapper<>();
        // // QueryWrapper<Section> section_wrapper = new QueryWrapper<>();
        // // position_wrapper.like("position_name", query);
        // // section_wrapper.like("section_name", query);
        // // wrapper.like("staff_id", query).or().like("staff_name",
        // query).or().like("sex", query).or()
        // // .like("staff_phone", query);
        // log.info("不应该进来的" + query);
        // list = staffInfoService.getListPage(wrapper);
        // } else {
        // log.info("正确的进来了");
        // list = staffInfoService.getListPage(params);
        // }
        list = staffInfoService.getListPage(params);
        // QueryWrapper
        // int a=4/0;
        return R.ok(list);
    }

    @PostMapping("/delete")
    public R delete(@RequestBody Long[] staff_ids) {
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        Long[] ids = new Long[1000];
        List<Long> staff_idList = Arrays.asList(staff_ids);
        wrapper.in("staff_id", staff_idList);
        List<Object> object = staffService.listObjs(wrapper);
        for (int i = 0; i < staff_ids.length; ++i) {
            Long id = (long) object.get(i).hashCode();
            ids[i] = id;
        }
        staffService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    // /**
    // * get
    // *
    // * @param id
    // * @return
    // */
    // @GetMapping("/info/{staff_id}")
    // public R info(@PathVariable("staff_id") Long staff_id) {
    // QueryWrapper<Staff> wrapper = new QueryWrapper<>();
    // wrapper.eq("staff_id", staff_id);
    // List<Object> object = staffService.listObjs(wrapper);
    // Long id = (long) object.get(0).hashCode();
    // search_staff = staffService.getById(id);
    // Position position = positionService.getById(search_staff.getPosition_id());
    // Section section = sectionService.getById(search_staff.getSection_id());
    // Map<String, Object> map = new HashMap<>();
    // map.put("staff_id", search_staff.getStaff_id());
    // map.put("staff_name", search_staff.getStaff_name());
    // map.put("sex", search_staff.getSex());
    // map.put("position_name", position.getPosition_name());
    // map.put("staff_phone", search_staff.getStaff_phone());
    // map.put("section_name", section.getSection_name());
    // return R.ok(map);
    // }

    @GetMapping("/info/{staff_id}")
    public R info(@PathVariable("staff_id") Long staff_id) {
        log.info("--msg: " + staff_id);
        Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("staff_id", staff_id));
        log.info("--msg--" + staff.getPositionId());
        Position position = positionService.getById(staff.getPositionId());
        Section section = sectionService.getById(staff.getSectionId());
        staff.setPositionName(position.getPositionName());
        staff.setSectionName(section.getSection_name());
        log.info("--msg--" + position.toString());
        log.info("--msg--" + staff.getPositionName());
        return R.ok(staff);
    }

    @PostMapping("/induction/update")
    public R update(@Validated @RequestBody Staff staff) {
        if (!url.equals("upload/avatar")) {
            staff.setAvatar(url);
        }
        staffService.updateById(staff);
        return R.ok(staff);
    }

    @PostMapping("/newList")
    public R newList(@RequestParam Map<String, Object> params) {
        IPage<Map<String, Object>> list;
        String OntrialBeginTime = (String) params.get("OntrialBeginTime");
        //String OntrialEndTime = (String) params.get("OntrialEndTime");
        if (OntrialBeginTime == ""){
            Calendar cale = Calendar.getInstance();
            int year = cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            String begin = year + "-" + month + "-1";
            String end = year + "-" + (month + 1) + "-1";
            params.put("OntrialBeginTime", begin);
            params.put("OntrialEndTime", end);
        }
        list = staffInfoService.getNewStaffListPage(params);
        return R.ok(list);
    }

    // /**
    // * save
    // *
    // * @Validated 配合后端验证
    // *
    // *
    // * @param blog
    // * @return
    // */
    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("/save")
    public R save(@RequestParam Map<String, Object> params) {
        QueryWrapper<Position> wrapper = new QueryWrapper<>();
        QueryWrapper<Section> wrapper1 = new QueryWrapper<>();
        Long staff_id = Long.parseLong((String) params.get("staff_id"));
        String staff_name = (String) params.get("staff_name");
        String sex = (String) params.get("sex");
        String staff_phone = (String) params.get("staff_phone");
        String position_name = (String) params.get("position_name");
        wrapper.eq("position_name", position_name);
        String section_name = (String) params.get("section_name");
        wrapper1.eq("section_name", section_name);
        List<Object> positionList = positionService.listObjs(wrapper);
        List<Object> sectionList = sectionService.listObjs(wrapper1);
        Long position_id = (long) positionList.get(0).hashCode();
        Long section_id = (long) sectionList.get(0).hashCode();
        Staff staff = new Staff(staff_id, staff_name, sex, position_id, staff_phone, section_id);
        // int a=4/0;
        staffInfoService.save(staff);
        return R.ok();
    }

    @PostMapping("/addStaff/save")
    public R AddStaff(@Validated @RequestBody Staff staff) {
        if (!url.equals("upload/avatar")) {
            staff.setAvatar(url);
        }
        staffService.addStaff(staff);
        return R.ok();
    }

    @PostMapping("/addStaff/avatar")
    public R uploadAvatarHandler(@RequestParam("avatar") MultipartFile uploadFile) throws IOException {
        // 文件名
        String fileName = uploadFile.getOriginalFilename();
        // 在file文件夹中创建名为fileName的文件
        assert fileName != null;
        int split = fileName.lastIndexOf(".");
        // 文件后缀，用于判断上传的文件是否是合法的
        String suffix = fileName.substring(split + 1, fileName.length());
        // 判断文件类型，因为我这边是图片，所以只设置三种合法格式
        if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
            // 正确的类型，保存文件

            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), url);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String newName = System.currentTimeMillis() + "." + suffix;
            String filepath = upload + "/" + newName;
            File savedFile = new File(filepath);
            uploadFile.transferTo(savedFile);
            url = url + "/" + newName;

            System.out.println("图片上传完毕，存储地址为：" + url);

        } else {
            R.error();

        }
        // {code: 200, msg: "操作成功", data: "upload/avatar/1626939612776.jpg"}
        return R.ok(url);

    }

    // /**
    // *
    // *
    // * @param blog
    // * @return
    // */
    @PostMapping("/update")
    public R update(@RequestParam Map<String, Object> params) {
        QueryWrapper<Position> wrapper = new QueryWrapper<>();
        QueryWrapper<Section> wrapper1 = new QueryWrapper<>();
        Long staff_id = Long.parseLong((String) params.get("staff_id"));
        String staff_name = (String) params.get("staff_name");
        String sex = (String) params.get("sex");
        String staff_phone = (String) params.get("staff_phone");
        String position_name = (String) params.get("position_name");
        wrapper.eq("position_name", position_name);
        String section_name = (String) params.get("section_name");
        wrapper1.eq("section_name", section_name);
        List<Object> positionList = positionService.listObjs(wrapper);
        List<Object> sectionList = sectionService.listObjs(wrapper1);
        Long position_id = (long) positionList.get(0).hashCode();
        Long section_id = (long) sectionList.get(0).hashCode();
        search_staff.setStaffId(staff_id);
        search_staff.setStaffName(staff_name);
        search_staff.setSex(sex);
        search_staff.setStaffPhone(staff_phone);
        search_staff.setPositionId(position_id);
        search_staff.setSectionId(section_id);
        staffService.updateById(search_staff);
        return R.ok();
    }

}
