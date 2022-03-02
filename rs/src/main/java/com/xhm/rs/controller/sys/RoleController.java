package com.xhm.rs.controller.sys;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xhm.rs.common.core.R;
import com.xhm.rs.entity.Role;
import com.xhm.rs.entity.User;
import com.xhm.rs.service.RoleService;
import com.xhm.rs.service.UserRoleService;
import com.xhm.rs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * controller调用service方法
 * 
 * 尽量保持controller层代码优雅
 * 
 * 
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    // 注入  
    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;


    @PostMapping("/list")
    public R list() {    
        List<Role> list = roleService.list();
        return R.ok(list);
    }



    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        Role entity = roleService.getById(id);
        return R.ok(entity);
    }


    @PostMapping("/save")
    public R save(@Validated @RequestBody Role entity) {
        roleService.save(entity);
        return R.ok();
    }


    @PostMapping("/update")
    public R update(@Validated @RequestBody Role entity) {
        roleService.updateById(entity);
        return R.ok();
    }



    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids) {


        roleService.deleteUserAndRole(ids);


        return R.ok();
    }
   
    /**
     * 分配角色
     * @param id
     * @return
     */
    @GetMapping("/transfer/{id}")
    public R transfer (@PathVariable("id") Long id) {

        //根据角色id查询分配给了哪些用户
        List<User> yesdis=userRoleService.getUserByRoleId(id);

        //查询所有用户
        List<User> all=userService.list();

        
        //返回多个结果，使用map
        Map<String,Object> m= new HashMap<String,Object>();
        m.put("yesdis", yesdis);
        m.put("all", all);
        return R.ok(m);
    }

    
    /**
     * 保存分配
     * @param ids
     * @return
     */
    @PostMapping("/savetransfer")
    public R savetransfer(@RequestBody Long[] ids) {
        
        System.out.println(ids);
        
        //用户id数组
        Long[] uids=Arrays.copyOf(ids,ids.length-1);
        //角色id
        Long rid=ids[ids.length-1];

         
        //关系保存到sys_user_role
        roleService.savetransfer(rid, uids);

        return R.ok();
    }





    
  
}
