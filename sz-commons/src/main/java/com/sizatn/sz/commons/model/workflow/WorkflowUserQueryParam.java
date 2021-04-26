package com.sizatn.sz.commons.model.workflow;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @desc flowable人员查询参数
 * @author sizatn
 * @date May 5, 2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("flowable人员查询参数")
public class WorkflowUserQueryParam {

	@ApiModelProperty("用户ID")
	private String userId;

	@ApiModelProperty("用户ID集合")
	private List<String> userIds;

	@ApiModelProperty("角色ID")
	private String roleId;

	@ApiModelProperty("角色ID集合")
	private List<String> roleIds;

	@ApiModelProperty("部门ID")
	private String deptId;

	@ApiModelProperty("部门ID集合")
	private List<String> deptIds;

	@ApiModelProperty("机构ID")
	private String orgId;

	@ApiModelProperty("机构ID集合")
	private List<String> orgIds;

	@ApiModelProperty("岗位ID")
	private String postId;

	@ApiModelProperty("岗位ID集合")
	private List<String> postIds;

	@ApiModelProperty("id包涵")
	private List<String> userIdIn;

	@ApiModelProperty("id排除")
	private List<String> userIdNotIn;

	@ApiModelProperty("姓名模糊查询")
	private String realNameLike;

	public WorkflowUserQueryParam(String userId, List<String> userIds, String roleId, List<String> roleIds,
			String deptId, List<String> deptIds, String orgId, List<String> orgIds, String postId,
			List<String> postIds) {
		this.userId = userId;
		this.userIds = userIds;
		this.roleId = roleId;
		this.roleIds = roleIds;
		this.deptId = deptId;
		this.deptIds = deptIds;
		this.orgId = orgId;
		this.orgIds = orgIds;
		this.postId = postId;
		this.postIds = postIds;
	}

	public WorkflowUserQueryParam(String deptId, List<String> userIdIn, List<String> userIdNotIn, String realNameLike) {
		this.deptId = deptId;
		this.userIdIn = userIdIn;
		this.userIdNotIn = userIdNotIn;
		this.realNameLike = realNameLike;
	}

}