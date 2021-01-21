package com.sizatn.sz.common.model.workflow;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @desc flowable机构组查询参数
 * @author sizatn
 * @date May 5, 2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("flowable机构组查询参数")
public class WorkflowOrgQueryParam {

	@ApiModelProperty("用户ID")
	private String userId;

	@ApiModelProperty("用户ID集合")
	private List<String> userIds;

	@ApiModelProperty("机构ID")
	private String orgId;

	@ApiModelProperty("机构ID集合")
	private List<String> orgIds;
}