package com.sizatn.sz.commons.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sizatn.sz.commons.model.PageParam;
import com.sizatn.sz.commons.query.QueryGenerator;
import com.sizatn.sz.utils.StringUtil;

/**
 * 
 * @desc Controller基类，提供常用的基类方法
 * @author sizatn
 * @date May 5, 2018
 */
@Component
public class BaseController<T> {

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private HttpServletResponse res;

	/**
	 * @param t
	 * @param pageParam
	 * @return QueryWrapper<T>
	 * @Description 构建查询参数
	 */
	@SuppressWarnings("unchecked")
	public QueryWrapper<T> buildQuery(T t, PageParam pageParam) {
		// QueryWrapper<T> queryWrapper = new QueryWrapper<T>(t);
		QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(t, req.getParameterMap());

		// 排序处理
		String columns = pageParam.getColumns();
		String order = pageParam.getOrder();
		if (StringUtil.isNotBlank(columns) && StringUtil.isNotBlank(order)) {
			String[] tempColumnArray = columns.split(",");
			String[] columnArray = new String[tempColumnArray.length];
			for (int i = 0; i < tempColumnArray.length; i++) {
				String column = StringUtil.camelToUnderline(tempColumnArray[i]);
				columnArray[i] = column;
			}
			if ("asc".equals(order)) {
				queryWrapper.orderByAsc(columnArray);
			} else {
				queryWrapper.orderByDesc(columnArray);
			}
		}
		return queryWrapper;
	}

	@SuppressWarnings("unchecked")
	public QueryWrapper<T> buildQuery(T t) {
		QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(t, req.getParameterMap());
		return queryWrapper;
	}

	/**
	 * @param pageParam
	 * @return Page<T>
	 * @Description 构建分页参数
	 */
	public Page<T> buildPage(PageParam pageParam) {
		return new Page<T>(pageParam.getPageNo(), pageParam.getPageSize());
	}

	/**
	 * @Description 根据list手动分页
	 * @param pageParam
	 * @param list
	 * @return
	 */
	public Page<T> buildPage(PageParam pageParam, List<T> list) {
		Page<T> page = new Page<T>(pageParam.getPageNo(), pageParam.getPageSize());
		if (list != null && !list.isEmpty()) {
			// page
			page.setTotal(list.size());
			if (page.getTotal() % page.getSize() == 0) {
				page.setPages(page.getTotal() / page.getSize());
			} else {
				page.setPages((page.getTotal() / page.getSize()) + 1);
			}
			// list
			long startSize = 0;
			long endSize = 0;
			if (page.getCurrent() < page.getPages()) {
				startSize = (page.getCurrent() - 1) * page.getSize();
				endSize = startSize + page.getSize();
			} else if (page.getCurrent() > page.getPages()) {
				page.setCurrent(page.getPages());
				startSize = (page.getCurrent() - 1) * page.getSize();
				endSize = page.getTotal();
			} else {
				// eq
				startSize = (page.getCurrent() - 1) * page.getSize();
				endSize = page.getTotal();
			}
			page.setRecords(list.subList((int) startSize, (int) endSize));
		} else {
			page.setTotal(0);
			page.setPages(0);
			page.setRecords(list);
		}
		return page;
	}

	public HttpServletRequest getReq() {
		return req;
	}

	public HttpServletResponse getRes() {
		return res;
	}
}
