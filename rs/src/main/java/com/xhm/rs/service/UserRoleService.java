package com.xhm.rs.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhm.rs.entity.Role;
import com.xhm.rs.entity.User;
import com.xhm.rs.entity.UserRole;


/**
 * extends IService
 */
public interface UserRoleService extends IService<UserRole>{


    
    /**
     * 根据用户ID，查询拥有的所有角色
     * @param userId
     * @return
     */
     public List<Role>  getRoleByUserId(Long  userId);



    /**
     * 根据角色ID，查询分配到的所有用户
     * @param roleId
     * @return
     */
     public List<User>  getUserByRoleId(Long roleId);
     
    
}
