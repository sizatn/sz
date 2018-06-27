package com.sizatn.sz.webapp.service;

import com.sizatn.sz.webapp.entityDTO.LoginDTO;

/**
 * 
 * @desc 登录Service
 * @author sizatn
 * @date Jun 27, 2018
 */
public interface LoginService {

	/**
	 * @param loginDTO
	 * @return String
	 * @desc 登录
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	String login(LoginDTO loginDTO);

	/**
	 * @return boolean
	 * @desc 登出
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	boolean logout();

}
