package com.sizatn.sz.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @desc 字典注解
 * @author sizatn
 * @date May 5, 2018
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

	/**
	 * @desc 字典缓存code
	 * @return
	 */
	String dictCode();

	/**
	 * @desc 转换后目标字段
	 * @return
	 */
	String target();
}
