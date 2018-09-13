package com.sizatn.sz.webapp.configuration.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @desc 
 * @author sizatn
 * @date May 15, 2018
 */
public class AjaxAuthorizationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		JSONObject jo = new JSONObject();
		jo.put("code", 401);
		jo.put("message", "未授权：登录失败");
		PrintWriter pw = null;
		HttpServletResponse res = (HttpServletResponse) request;
		try {
			res.setCharacterEncoding("UTF_8");
			res.setContentType("application/json;charset=utf-8");
			pw = res.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
		return false;
	}

}
