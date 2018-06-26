package com.sizatn.sz.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sizatn.sz.webapp.dao.UserMapper;
import com.sizatn.sz.webapp.entity.User;
import com.sizatn.sz.webapp.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String userId) {
		return userMapper.selectById(userId);
	}

}
