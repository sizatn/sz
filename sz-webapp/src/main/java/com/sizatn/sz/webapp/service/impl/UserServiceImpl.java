package com.sizatn.sz.webapp.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sizatn.sz.webapp.dao.UserMapper;
import com.sizatn.sz.webapp.entity.User;
import com.sizatn.sz.webapp.service.IUserService;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService { 

	@Override
	public User getUser(String userId) {
		return baseMapper.selectById(userId);
	}

}
