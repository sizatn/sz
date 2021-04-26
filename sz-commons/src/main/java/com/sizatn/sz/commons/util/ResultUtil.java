package com.sizatn.sz.commons.util;

import com.sizatn.sz.commons.constant.Constants;
import com.sizatn.sz.commons.model.Result;

/**
 * 
 * @desc response返回结果工具类
 * @author sizatn
 * @date Jun 26, 2018
 */
public class ResultUtil {

	public static <T> Result<T> success() {
		return new Result<T>(Constants.SC_OK_200, Constants.SUCCESS_MSG);
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>(Constants.SC_OK_200, Constants.SUCCESS_MSG, data);
	}

	public static <T> Result<T> success(String message, T data) {
		return new Result<T>(Constants.SC_OK_200, message, data);
	}

	public static <T> Result<T> success(String message) {
		return new Result<T>(Constants.SC_OK_200, message);
	}

	public static <T> Result<T> error() {
		return new Result<T>(Constants.SC_INTERNAL_SERVER_ERROR_500, Constants.ERROR_MSG);
	}

	public static <T> Result<T> error(String message) {
		return new Result<T>(Constants.SC_INTERNAL_SERVER_ERROR_500, message);
	}

	public static <T> Result<T> error(Integer code, String message) {
		return new Result<T>(code, message);
	}

}
