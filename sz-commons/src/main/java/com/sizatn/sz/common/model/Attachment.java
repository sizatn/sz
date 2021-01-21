package com.sizatn.sz.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @desc 文件/附件上传下载模型
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("文件/附件上传下载模型")
public class Attachment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("文件名称")
	private String name;

	@ApiModelProperty("文件扩展名")
	private String suffix;

	@ApiModelProperty("文件存储名称")
	private String realName;

	@ApiModelProperty("文件内容(DB时使用)")
	private byte[] content;

}
