package com.sizatn.sz.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.sizatn.sz.common.global.ErrorEnum;
import com.sizatn.sz.utils.response.ResponseResult;
import com.sizatn.sz.utils.response.ResultUtil;
import com.sizatn.sz.webapp.entityDTO.LoginDTO;
import com.sizatn.sz.webapp.service.LoginService;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * @param loginDTO
	 * @return ResponseResult
	 * @desc 登录
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	@PostMapping("login")
	public ResponseResult login(@RequestBody LoginDTO loginDTO) {
		try {
			if (Strings.isNullOrEmpty(loginDTO.getUsername())) {
				return ResultUtil.error("登录名不能为空");
			} else if (Strings.isNullOrEmpty(loginDTO.getPassword())) {
				return ResultUtil.error("密码不能为空");
			} else {
				Map<String, Object> resultMap = new HashMap<String, Object>(1);
				String token = loginService.login(loginDTO);
				if (!Strings.isNullOrEmpty(token)) {
					resultMap.put("token", token);
					return ResultUtil.success(resultMap, "登录成功");
				} else {
					return ResultUtil.error("登录失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.error(ErrorEnum.E_400.getErrorCode(), ErrorEnum.E_400.getErrorMsg());
	}

	/**
	 * @return ResponseResult
	 * @desc 登出
	 * @author sizatn
	 * @date Jun 27, 2018
	 */
	@GetMapping("logout")
	public ResponseResult logout() {
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
		return ResultUtil.error(ErrorEnum.E_400.getErrorCode(), ErrorEnum.E_400.getErrorMsg());
	}

}
