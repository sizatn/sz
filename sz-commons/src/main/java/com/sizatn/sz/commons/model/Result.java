package com.sizatn.sz.commons.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @desc 通用接口返回数据对象
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@ApiModel("返回数据对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "成功标识")
	private boolean success = true;

	@ApiModelProperty(value = "返回消息")
	private String message = "";

	@ApiModelProperty(value = "返回代码")
	private Integer code;

	@ApiModelProperty(value = "数据对象")
	private T result;

	@ApiModelProperty(value = "时间戳")
	private long timestamp;

	public Result() {
	}

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}

	public Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.result = data;
		this.timestamp = System.currentTimeMillis();
	}

}
