package com.sizatn.sz.webapp.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sizatn.sz.webapp.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;

}
