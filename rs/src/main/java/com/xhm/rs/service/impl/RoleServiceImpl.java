package com.xhm.rs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.dao.RoleDao;
import com.xhm.rs.entity.Role;
import com.xhm.rs.entity.UserRole;
import com.xhm.rs.service.RoleService;
import com.xhm.rs.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  extends ServiceImpl
 * 
 *   
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
   
   @Autowired
   UserRoleService userRoleService;

   
    /**
     * 一个方法里有多个增删改，加事务
     */
    @Transactional(rollbackFor = Exception.class)
    public  void savetransfer(Long rid,Long[] uids){

          //2.删除所有旧关系  
       userRoleService.remove(new QueryWrapper<UserRole>().eq("role_id", rid));
      
      
         //3.添加新关系   INSERT INTO sys_user_role ( user_id, role_id ) VALUES ( ?, ? )
         //保存角色
         List<UserRole> list=new ArrayList<UserRole>();
         for(Long uid : uids){
             UserRole ur=new UserRole();
             ur.setUserId(uid);
             ur.setRoleId(rid);
             list.add(ur);
         }

         userRoleService.saveBatch(list);   //批量保存角色
    }


    /**
    * 删除用户和角色
    */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserAndRole(Long[] ids){
          //1.删除自身  
          this.removeByIds(Arrays.asList(ids));

          //2.删除关系  DELETE FROM sys_user_role WHERE (user_id = ?)
          for(Long id : ids){
              userRoleService.remove(new QueryWrapper<UserRole>().eq("role_id", id));
          }
    }
   
}
