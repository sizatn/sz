package com.sizatn.sz.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @desc 分页模型
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@ApiModel("分页模型")
public class PageParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "页码", example = "1")
	Integer pageNo = 1;

	@ApiModelProperty(value = "分页大小", example = "10")
	Integer pageSize = 10;

	@ApiModelProperty(value = "排序字段")
	String columns;

	@ApiModelProperty(value = "排序方式")
	String order;

	public PageParam() {

	}

	public PageParam(Integer pageNo, Integer pageSize) {
		if (pageNo != null) {
			this.pageNo = pageNo;
		} else {
			this.pageNo = 1;
		}
		if (pageSize != null) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = 10;
		}
	}

	public PageParam(Integer pageNo, Integer pageSize, String columns, String order) {
		if (pageNo != null) {
			this.pageNo = pageNo;
		}
		if (pageSize != null) {
			this.pageSize = pageSize;
		}
		if (columns != null) {
			this.columns = columns;
		}
		if (order != null) {
			this.order = order;
		}
	}
}
