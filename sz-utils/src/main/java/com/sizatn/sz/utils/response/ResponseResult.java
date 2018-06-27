package com.sizatn.sz.utils.response;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @desc response返回结果对象
 * @author sizatn
 * @date Jun 26, 2018
 */
@Data
public class ResponseResult implements Serializable {

	private static final long serialVersionUID = 4021284293832209216L;

	private String code; // http 状态码
	private String message; // 返回信息
	private Object data; // 返回的数据

	public ResponseResult() {

	}

	public ResponseResult(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResponseResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
