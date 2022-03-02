package com.xhm.rs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色
 * 
 */

@TableName("sys_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 
	 */
	private String rolePerms;
	/**
	 * 备注
	 */
	private String remark;
     


    
	 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /**
	 * 设置：角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：
	 */
	public void setRolePerms(String rolePerms) {
		this.rolePerms = rolePerms;
	}
	/**
	 * 获取：
	 */
	public String getRolePerms() {
		return rolePerms;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}