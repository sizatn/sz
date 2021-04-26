package com.sizatn.sz.webapp.configuration.shiro;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.sizatn.sz.webapp.entity.User;
import com.sizatn.sz.webapp.service.IUserService;

/**
 * 
 * @desc 自定义Realm
 * @author sizatn
 * @date May 15, 2018
 */
public class WebappRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;

	/** 
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		String userString = (String) session.getAttribute("userinfo");
		return null;
	}

	/** 
	 * 获取身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userId = (String) token.getPrincipal();
		// 
		User user = userService.getUser(userId);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserId(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		// session不保存密码
		user.setPassword("");
		SecurityUtils.getSubject().getSession().setAttribute("userinfo", JSONObject.toJSONString(user));
		return authenticationInfo;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphanumeric(16));
	}

}
