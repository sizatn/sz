package com.sizatn.sz.common.constant;

/**
 * 
 * @desc 通用常量类, 单个模块或系统的常量请新建一个package，并以模块或系统命名, 方便常量的分类管理
 * @author sizatn
 * @date May 5, 2018
 */
public interface Constants {

	public static final String SUCCESS_CODE = "1";
	public static final String SUCCESS_MSG = "请求成功";

	public static final String ERROR_CODE = "0";
	public static final String ERROR_MSG = "请求失败";
	
	// 请求成功
    public static final Integer SC_OK_200 = 200;
    
    // Bad Request 客户端输入错误
    public static final Integer SC_BAD_REQUEST = 400;
    
    // Unauthorized 未认证或未授权
    public static final Integer SC_UNAUTHORIZED = 401;
    
    // Forbidden 禁止访问
    public static final Integer SC_FORBIDDEN = 403;
    
    // Not Found 找不到对象
    public static final Integer SC_NOT_FOUND = 404;
    
    // Conflict 对象冲突
    public static final Integer SC_CONFLICT = 409;
    
    // 服务端错误
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    
    // 系统日志类型： 登录日志
    public static int LOG_TYPE_0 = 0;

    // 系统日志类型： 操作日志
    public static int LOG_TYPE_1 = 1;
    
    // 系统日志类型： 定时任务日志
    public static int LOG_TYPE_2 = 2;

}
