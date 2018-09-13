package com.sizatn.sz.webapp.configuration.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @desc shiro的配置类
 * @author sizatn
 * @date May 15, 2018
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * shiro 过滤器
	 * 
	 * @param defaultWebSecurityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		Map<String, Filter> filters = new HashMap<>();
		filters.put("authc", new AjaxAuthorizationFilter());
		bean.setFilters(filters);
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>(4);
		// 匿名拦截器,不需要进行登录认证(例如静态资源) "/swagger-resources/**","/v2/api-docs"
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/loginout", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(webappRealm());
		return manager;
	}

	@Bean
	public WebappRealm webappRealm() {
		WebappRealm realm = new WebappRealm();
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("md5");
		matcher.setHashIterations(2);
		matcher.setStoredCredentialsHexEncoded(true);
		return matcher;
	}
	
	@Bean
	public FilterRegistrationBean<AjaxAuthorizationFilter> registration(AjaxAuthorizationFilter filter) {
		FilterRegistrationBean<AjaxAuthorizationFilter> registration = new FilterRegistrationBean<AjaxAuthorizationFilter>(filter);
		registration.setEnabled(false);
		return registration;
	}

}
