package com.sizatn.sz.webapp.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sizatn.sz.webapp.entityDTO.LoginDTO;
import com.sizatn.sz.webapp.service.ILoginService;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Override
	public String login(LoginDTO loginDTO) {
		String result = "";
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				result = subject.getSession().getId().toString();
			} else {
				token.clear();
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
