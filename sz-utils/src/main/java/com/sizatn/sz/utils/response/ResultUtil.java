package com.sizatn.sz.utils.response;

/**
 * 
 * @desc response返回结果工具类
 * @author sizatn
 * @date Jun 26, 2018
 */
public class ResultUtil {

	private static final String SUCCESS_CODE = "666666";
	private static final String SUCCESS_MSG = "请求成功";

	private static final String ERROR_CODE = "000000";
	private static final String ERROR_MSG = "请求失败";

	public static ResponseResult success() {
		return new ResponseResult(SUCCESS_CODE, SUCCESS_MSG);
	}

	public static ResponseResult success(Object data) {
		return new ResponseResult(SUCCESS_CODE, SUCCESS_MSG, data);
	}

	public static ResponseResult error() {
		return new ResponseResult(ERROR_CODE, ERROR_MSG);
	}

	public static ResponseResult error(String message) {
		return new ResponseResult(ERROR_CODE, message);
	}

}
