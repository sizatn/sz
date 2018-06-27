package com.sizatn.sz.webapp.service;

import com.sizatn.sz.utils.response.ResponseResult;
import com.sizatn.sz.webapp.entityDTO.LoginDTO;

/**
 * 
 * @desc 登录Service
 * @author sizatn
 * @date Jun 27, 2018
 */
public interface LoginService {

	ResponseResult login(LoginDTO loginDTO);

	ResponseResult logout();

}
