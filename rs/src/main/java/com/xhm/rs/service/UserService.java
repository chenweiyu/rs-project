package com.xhm.rs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.User;


/**
 * 1.继承了哪些方法，直接使用
 * 
 * 2.自定义方法怎么写
 * 
 */
public interface UserService extends IService<User> {

    
   /**
    * 保存用户和角色
    */
   public void saveUserAndRole(User entity);

   
    /**
    * 更新用户和角色
    */
    public void updateUserAndRole(User entity);


    /**
    * 删除用户和角色
    */
    public void deleteUserAndRole(Long[] ids);





    //      /*
    //   多表 
    //   自定义方法：
    //  */   
    // public IPage<Map<String, Object>>  q1(Map<String, Object> params);

}
