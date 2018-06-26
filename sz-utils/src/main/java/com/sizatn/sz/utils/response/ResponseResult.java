package com.sizatn.sz.utils.response;

import java.io.Serializable;

/**
 * 
 * @desc response返回结果对象
 * @author sizatn
 * @date Jun 26, 2018
 */
public class ResponseResult implements Serializable {

	private static final long serialVersionUID = 4021284293832209216L;

	private String code; // http 状态码
	private String message; // 返回信息
	private Object data; // 返回的数据

	public ResponseResult(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public ResponseResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
