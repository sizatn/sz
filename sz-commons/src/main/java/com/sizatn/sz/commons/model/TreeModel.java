package com.sizatn.sz.commons.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @desc 树模型
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@ApiModel("树模型")
public class TreeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty
    private String key;

    @ApiModelProperty
    private String title;

    @ApiModelProperty
    private boolean isLeaf;

    @ApiModelProperty
    private String icon;
    
    @ApiModelProperty
    private String parentId;

    @ApiModelProperty
    private String label;

    @ApiModelProperty
    private String value;

    @ApiModelProperty
    private List<TreeModel> children = new ArrayList<TreeModel>();

    public TreeModel() {

    }
	
//    public TreeModel convert(SOrgDeptTreeModel sOrgDeptTreeModel) {
//    	this.key = sOrgDeptTreeModel.getUuid();
//    	this.value = sOrgDeptTreeModel.getParentId();
//    	this.title = sOrgDeptTreeModel.getName();
//    	return this;
//    }

}
