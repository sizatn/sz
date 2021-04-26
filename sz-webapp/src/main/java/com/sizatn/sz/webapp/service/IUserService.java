package com.sizatn.sz.webapp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sizatn.sz.webapp.entity.User;

public interface IUserService extends IService<User> {

	User getUser(String userId);

}
