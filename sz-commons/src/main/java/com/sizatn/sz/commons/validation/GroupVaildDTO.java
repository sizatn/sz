package com.sizatn.sz.commons.validation;

import javax.validation.groups.Default;

/**
 * 
 * @desc Spring validation 分组校验接口
 * @author sizatn
 * @date May 5, 2018
 */
public class GroupVaildDTO {
	
	public interface AddGroup extends Default {
	}

	public interface UpdateGroup extends Default {
	}
	
}
