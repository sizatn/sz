package com.sizatn.sz.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @desc 树节点
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@ApiModel("树节点")
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "节点i", position = 1)
	private String id;

	@ApiModelProperty(value = "节点名称", position = 2)
	private String title;

	@ApiModelProperty(value = "父节点id", position = 3)
	private String parentId;

	@ApiModelProperty(value = "节点类型", position = 4)
	private String type;

	@ApiModelProperty(value = "是否叶子节点", position = 5)
	private Boolean isLeaf;

	@ApiModelProperty(value = "子节点列表", position = 5)
	private List<TreeNode> children;

}
