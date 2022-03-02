package com.xhm.rs.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * user 实体类
 * 
 * 1. @TableName @TableId @TableField 三个注解 2. 遵循驼峰命名 user_name ：userName 3.
 * 了解约定规则 4. 时间 建议 LocalDateTime
 * 
 */

@TableName("user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId
	private Long id;

	private String name;

	private String password;


	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;

	private String email;

	private String position;

    //当前用户选中的角色ID数组
	@TableField(exist = false)
	private Long[]  yesCheckList;

 
    

    @TableField(exist = false)
	private List<Role> myRoleList;


	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;


    

	public List<Role> getMyRoleList() {
		return myRoleList;
	}

	public void setMyRoleList(List<Role> myRoleList) {
		this.myRoleList = myRoleList;
	}

 

	public Long[] getYesCheckList() {
		return yesCheckList;
	}

	public void setYesCheckList(Long[] yesCheckList) {
		this.yesCheckList = yesCheckList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	 
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	 

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
