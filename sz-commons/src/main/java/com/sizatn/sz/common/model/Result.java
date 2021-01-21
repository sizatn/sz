package com.sizatn.sz.common.model;

import java.io.Serializable;

import com.sizatn.sz.common.constant.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @desc 通用接口返回数据
 * @author sizatn
 * @date May 5, 2018
 */
@ApiModel("返回数据")
@Data
public class Result<T> implements Serializable {

	private final static int successCode = 200;
	private final static int errorCode = 400;
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "成功标识")
	private boolean success = true;

	@ApiModelProperty(value = "返回消息")
	private String message = "";

	@ApiModelProperty(value = "返回代码", example = "200")
	private Integer code = Constants.SC_OK_200;

	@ApiModelProperty(value = "数据对象")
	private T result;

	@ApiModelProperty(value = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public Result() {

	}

	public Result(int code, String mesg, T data) {
		this.code = code;
		this.message = mesg;
		this.result = data;
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>(Constants.SC_OK_200, "", data);
	}

	public static <T> Result<T> fail() {
		return new Result<T>(errorCode, "", null);
	}

	public void error500(String message) {
		this.message = message;
		this.code = Constants.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void error(int code, String message, T result) {
		this.success = false;
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public void error(int code, String message) {
		error(code, message, null);
	}

	public void error(String msg) {
		error(Constants.SC_INTERNAL_SERVER_ERROR_500, msg);
	}

	public static <T> Result<T> buildError(String msg) {
		return buildError(Constants.SC_INTERNAL_SERVER_ERROR_500, msg);
	}

	public static <T> Result<T> buildError(int code, String msg) {
		return buildError(code, msg, null);
	}

	public static <T> Result<T> buildError(int code, String msg, T result) {
		Result<T> r = new Result<>();
		r.error(code, msg, result);
		return r;
	}

	public void success(String message) {
		this.message = message;
		this.code = Constants.SC_OK_200;
		this.success = true;
	}

	public void success(String message, T result) {
		success(message);
		this.result = result;
	}

	public static <T> Result<T> buildSuccess(String msg) {
		return buildSuccess(msg, null);
	}

	public static <T> Result<T> buildSuccess(String message, T obj) {
		Result<T> r = new Result<>();
		r.success(message, obj);
		return r;
	}

	public static boolean isSuccess(Object flag) {
		if (flag instanceof Integer) {
			return successCode == (Integer) flag;
		} else if (flag instanceof String) {
			return String.valueOf(successCode).equals(flag);
		} else {
			return false;
		}
	}

}
