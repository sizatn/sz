package com.sizatn.sz.webapp.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * @desc druid数据源监控
 * @author sizatn
 * @date Sep 13, 2018
 */
@Configuration
public class DruidMonitorConfig {

	/**
	 * 注册ServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");

		/** 初始化参数配置，initParams **/
		// 登录查看信息的账号密码.
		bean.addInitParameter("loginUsername", "admin");
		bean.addInitParameter("loginPassword", "admin");
		// IP白名单 (没有配置或者为空，则允许所有访问)
		bean.addInitParameter("allow", "");
		// IP黑名单 (共存时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		bean.addInitParameter("deny", "192.88.88.88");
		// 禁用HTML页面上的“Reset All”功能
		bean.addInitParameter("resetEnable", "false");

		return bean;
	}

	/**
	 * 注册FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> druidWebStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
		// 添加过滤规则.
		bean.addUrlPatterns("/*");
		// 添加不需要忽略的格式信息.
		bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return bean;
	}
}
