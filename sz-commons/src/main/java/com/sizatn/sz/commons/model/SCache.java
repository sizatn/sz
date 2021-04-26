package com.sizatn.sz.commons.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @desc 缓存模型
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@NoArgsConstructor
@ApiModel("缓存模型")
public class SCache {

	@ApiModelProperty(value = "缓存编码", position = 0)
	private String code;

	@ApiModelProperty(value = "缓存值", position = 0)
	private String value;
	
	public SCache(String code, String value) {
		this.code = code;
		this.value = value;
	}
}
