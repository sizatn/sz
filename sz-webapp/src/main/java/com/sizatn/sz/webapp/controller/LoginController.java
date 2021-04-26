package com.sizatn.sz.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.sizatn.sz.commons.model.Result;
import com.sizatn.sz.commons.util.ResultUtil;
import com.sizatn.sz.webapp.entityDTO.LoginDTO;
import com.sizatn.sz.webapp.service.ILoginService;

import io.swagger.annotations.Api;

@Api(tags = "登录管理", description = "登录管理")
@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	private ILoginService loginService;

	/**
	 * @param loginDTO
	 * @return ResponseResult
	 * @desc 登录	
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	@PostMapping("login")
	public Result<String> login(@RequestBody LoginDTO loginDTO) {
		try {
			if (Strings.isNullOrEmpty(loginDTO.getUsername())) {
				return ResultUtil.error("登录名不能为空");
			} else if (Strings.isNullOrEmpty(loginDTO.getPassword())) {
				return ResultUtil.error("密码不能为空");
			} else {
				String token = loginService.login(loginDTO);
				if (!Strings.isNullOrEmpty(token)) {
					return ResultUtil.success(token, "登录成功");
				} else {
					return ResultUtil.error("登录失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.error("登陆失败");
	}

	/**
	 * @return ResponseResult
	 * @desc 登出
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	@GetMapping("logout")
	public Result<String> logout() {
		try {
			boolean b = loginService.logout();
			if (b) {
				return ResultUtil.success("登出成功");
			} else {
				return ResultUtil.error("登出失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.error("登出失败");
	}

}
