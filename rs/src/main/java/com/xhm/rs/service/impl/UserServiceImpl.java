package com.xhm.rs.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhm.rs.dao.UserDao;
import com.xhm.rs.entity.User;
import com.xhm.rs.entity.UserRole;
import com.xhm.rs.service.UserRoleService;
import com.xhm.rs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
   
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleService  userRoleService;


     /**
       * 保存用户和角色 

      */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndRole(User entity) {
        //保存用户
        entity.setCreateTime(LocalDateTime.now());
        this.save(entity);    //回传会 id 
        System.out.println(entity);
       
        //保存角色
        List<UserRole> list=new ArrayList<UserRole>();
        for(Long rid : entity.getYesCheckList()){
            UserRole ur=new UserRole();
            ur.setUserId(entity.getId());
            ur.setRoleId(rid);
            list.add(ur);
        }

        System.out.println(list);
         
        userRoleService.saveBatch(list);   //批量保存角色
        
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserAndRole(User entity) {
        //1.更新用户表  UPDATE sys_user SET name=?, password=?, birthday=?, email=?, avatar=?, create_time=? WHERE id=?
        this.updateById(entity);

        //2.删除所有旧关系  DELETE FROM sys_user_role WHERE (user_id = ?)
       userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", entity.getId()));
      
      
       //3.添加新关系   INSERT INTO sys_user_role ( user_id, role_id ) VALUES ( ?, ? )
         //保存角色
         List<UserRole> list=new ArrayList<UserRole>();
         for(Long rid : entity.getYesCheckList()){
             UserRole ur=new UserRole();
             ur.setUserId(entity.getId());
             ur.setRoleId(rid);
             list.add(ur);
         }

         userRoleService.saveBatch(list);   //批量保存角色
              
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserAndRole(Long[] ids) {
        //1.删除自身 DELETE FROM sys_user WHERE id IN ( ? )
        this.removeByIds(Arrays.asList(ids));

        //2.删除关系  DELETE FROM sys_user_role WHERE (user_id = ?)
        for(Long id : ids){
            userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id));
        }
        
    }

   
    

}
