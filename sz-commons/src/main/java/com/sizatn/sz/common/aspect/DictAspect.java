package com.sizatn.sz.common.aspect;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sizatn.sz.common.annotation.Dict;
import com.sizatn.sz.common.model.Result;
import com.sizatn.sz.common.util.DictUtils;
import com.sizatn.sz.utils.common.ObjectUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @desc 字典aop类
 * @author sizatn
 * @date May 5, 2018
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

	@Autowired
	private DictUtils dictUtils;

	// 定义切点Pointcut
	@Pointcut("execution(public * com.sizatn..*Controller.*(..))")
	public void excudeService() {
	}

	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time1 = System.currentTimeMillis();
		Object result = pjp.proceed();
		long time2 = System.currentTimeMillis();
		log.debug("获取JSON数据 耗时：" + (time2 - time1) + "ms");
		long start = System.currentTimeMillis();
		this.parseDictValue(result);
		long end = System.currentTimeMillis();
		log.debug("解析注入JSON数据  耗时" + (end - start) + "ms");
		return result;
	}

	/**
	 * @desc 本方法针对返回对象为Result 的IPage的分页列表数据进行动态字典注入
	 * @desc 字典注入实现 通过对实体类添加注解@dict 来标识需要的字典内容,字典分为单字典code即可 ，table字典 code table
	 *       text配合使用与原来jeecg的用法相同
	 * @desc 示例为SysUser字段为sex 添加了注解@Dict(dicCode = "sex") 会在字典服务立马查出来对应的text
	 *       然后在请求list的时候将这个字典text，已字段名称加_dictText形式返回到前端
	 * @desc 例输入当前返回值的就会多出一个sex_dictText字段
	 * @desc {sex:1,sex_dictText:"男"}
	 * @param result
	 */
	private void parseDictValue(Object result) {
		if (result instanceof Result) {
			List<Object> records = null;
			if (((Result) result).getResult() instanceof IPage) {
				records = ((IPage) ((Result) result).getResult()).getRecords();
				if (records == null || records.size() == 0) {
					return;
				}
				List items = parseDictValue(records);
				((IPage) ((Result) result).getResult()).setRecords(items);
			}
			if (((Result) result).getResult() instanceof List) {
				records = (List<Object>) (((Result) result).getResult());
				if (records == null || records.size() == 0) {
					return;
				}
				List items = parseDictValue(records);
				((Result) result).setResult(items);
			}
		}
	}

	private List parseDictValue(List<Object> records) {
		List items = new ArrayList<>(records.size());
		Object record = records.get(0);
		ObjectMapper mapper = new ObjectMapper();
		String json = "{}";
		for (Object o : records) {
			try {
				// 解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
				json = mapper.writeValueAsString(o);
			} catch (JsonProcessingException e) {
				log.error("json解析失败" + e.getMessage(), e);
			}
			items.add(JSONObject.parseObject(json));
		}
		for (Field field : ObjectUtil.getAllFields(record)) {
			if (field.getAnnotation(Dict.class) != null) {
				String dictCode = field.getAnnotation(Dict.class).dictCode();// 缓存名称
				String keyColumn = field.getName();// 待转换的字段
				String targetColumn = field.getAnnotation(Dict.class).target();// 转换后存放的字段
				log.debug(" 字典 key : " + dictCode);
				dictUtils.dictToValueList(items, dictCode, keyColumn, targetColumn);
			}
			// date类型默认转换string格式化日期
			if (field.getType() == Date.class && field.getAnnotation(JsonFormat.class) == null) {
				SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (Object o : items) {
					JSONObject item = (JSONObject) o;
					if (item.get(field.getName()) != null) {
						item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
					}
				}
			}
		}
		return items;
	}

}
