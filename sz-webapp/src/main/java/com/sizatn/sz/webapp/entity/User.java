package com.sizatn.sz.webapp.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

@Data
@TableName
public class User {

	/**
	 * 用户ID
	 */
	@TableId
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 用户状态 0表示禁用，1表示正常，2表示逻辑删除
	 */
	private Short status;

	/**
	 * 最后一次登录系统时间
	 */
	private Date lastLoginTime;

	/**
	 * 记录创建者
	 */
	private String recordCreateBy;

	/**
	 * 记录创建时间
	 */
	private Date recordCreateTime;

	/**
	 * 记录更新者
	 */
	private String recordUpdateBy;

	/**
	 * 记录更新时间
	 */
	private Date recordUpdateTime;

}
