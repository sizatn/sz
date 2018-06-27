package com.sizatn.sz.webapp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.sizatn.sz.utils.response.ResponseResult;
import com.sizatn.sz.utils.response.ResultUtil;
import com.sizatn.sz.webapp.entityDTO.LoginDTO;
import com.sizatn.sz.webapp.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Override
	public ResponseResult login(LoginDTO loginDTO) {
		ResponseResult result = new ResponseResult();
		Map<String, Object> resultMap = new HashMap<String, Object>(1);
		if (Strings.isNullOrEmpty(loginDTO.getUsername())) {
			result = ResultUtil.error("登录名不能为空");
		} else if (Strings.isNullOrEmpty(loginDTO.getPassword())) {
			result = ResultUtil.error("密码不能为空");
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
					resultMap.put("token", subject.getSession().getId().toString());
					result = ResultUtil.success(resultMap, "登录成功");
				} else {
					token.clear();
					result = ResultUtil.error("登录失败");
				}
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ResponseResult logout() {
		ResponseResult js = new ResponseResult();
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			js = ResultUtil.success();
		} catch (Exception e) {
			js = ResultUtil.error("登出失败");
		}
		return js;
	}

}
