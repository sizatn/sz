package com.sizatn.sz.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sizatn.sz.common.constant.Constants;

/**
 * 
 * @desc  系统日志注解
 * @author sizatn
 * @date May 5, 2018
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * @desc 日志内容
     * @return
     */
    String value() default "";

    /**
     * @desc 日志类型（0:登录日志，1:操作日志，2:定时任务）
     * @return 
     */
    int logType() default Constants.LOG_TYPE_1;
}
