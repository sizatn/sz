package com.sizatn.sz.utils.response;

import com.sizatn.sz.common.global.Constants;

/**
 * 
 * @desc response返回结果工具类
 * @author sizatn
 * @date Jun 26, 2018
 */
public class ResultUtil {

	public static ResponseResult success() {
		return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
	}

	public static ResponseResult success(Object data) {
		return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, data);
	}

	public static ResponseResult success(Object data, String message) {
		return new ResponseResult(Constants.SUCCESS_CODE, message, data);
	}

	public static ResponseResult error() {
		return new ResponseResult(Constants.ERROR_CODE, Constants.ERROR_MSG);
	}

	public static ResponseResult error(String message) {
		return new ResponseResult(Constants.ERROR_CODE, message);
	}

}
