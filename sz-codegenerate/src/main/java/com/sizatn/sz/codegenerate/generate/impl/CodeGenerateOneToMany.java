package com.sizatn.sz.codegenerate.generate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sizatn.sz.codegenerate.config.CodeConfigProperties;
import com.sizatn.sz.codegenerate.database.DbReadTableUtil;
import com.sizatn.sz.codegenerate.generate.IGenerate;
import com.sizatn.sz.codegenerate.generate.base.CreateFileConfig;
import com.sizatn.sz.codegenerate.generate.impl.base.BaseCodeGenerate;
import com.sizatn.sz.codegenerate.generate.pojo.ColumnVo;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.MainTableVo;
import com.sizatn.sz.codegenerate.generate.pojo.onetomany.SubTableVo;
import com.sizatn.sz.codegenerate.generate.util.NonceUtils;

public class CodeGenerateOneToMany extends BaseCodeGenerate implements IGenerate {
	private static final Logger log = LoggerFactory.getLogger(CodeGenerateOneToMany.class);
	private static String e;
	public static String a = "A";
	public static String b = "B";
	private MainTableVo mainTableVo;
	private List<ColumnVo> mainColums;
	private List<ColumnVo> originalMainColumns;
	private List<SubTableVo> subTables;
	private static DbReadTableUtil j = new DbReadTableUtil();

	public CodeGenerateOneToMany(MainTableVo mainTableVo, List<SubTableVo> subTables) {
		this.subTables = subTables;
		this.mainTableVo = mainTableVo;
	}

	public CodeGenerateOneToMany(MainTableVo mainTableVo, List<ColumnVo> mainColums, List<ColumnVo> originalMainColumns,
			List<SubTableVo> subTables) {
		this.mainTableVo = mainTableVo;
		this.mainColums = mainColums;
		this.originalMainColumns = originalMainColumns;
		this.subTables = subTables;
	}

	public Map<String, Object> initData() throws Exception {
		HashMap<Object, Object> localHashMap = new HashMap<>();

		localHashMap.put("bussiPackage", CodeConfigProperties.bussiPackage);

		localHashMap.put("entityPackage", this.mainTableVo.getEntityPackage());

		localHashMap.put("entityName", this.mainTableVo.getEntityName());

		localHashMap.put("tableName", this.mainTableVo.getTableName());

		localHashMap.put("ftl_description", this.mainTableVo.getFtlDescription());

		localHashMap.put("primaryKeyField", CodeConfigProperties.DB_TABLE_ID);
		localHashMap.put("tableId", this.mainTableVo.getPrimaryKeyPolicy());
		if (this.mainTableVo.getFieldRequiredNum() == null) {
			this.mainTableVo.setFieldRequiredNum(
					Integer.valueOf(StringUtils.isNotEmpty(CodeConfigProperties.PAGE_FIELD_REQUIRED_NUM)
							? Integer.parseInt(CodeConfigProperties.PAGE_FIELD_REQUIRED_NUM)
							: -1));
		}
		if (this.mainTableVo.getSearchFieldNum() == null) {
			this.mainTableVo.setSearchFieldNum(
					Integer.valueOf(StringUtils.isNotEmpty(CodeConfigProperties.PAGE_FIELD_SEARCH_NUM)
							? Integer.parseInt(CodeConfigProperties.PAGE_FIELD_SEARCH_NUM)
							: -1));
		}
		if (this.mainTableVo.getFieldRowNum() == null) {
			this.mainTableVo.setFieldRowNum(Integer.valueOf(Integer.parseInt(CodeConfigProperties.q)));
		}
		localHashMap.put("tableVo", this.mainTableVo);

		try {
			if (this.mainColums == null || this.mainColums.size() == 0) {
				this.mainColums = DbReadTableUtil.readTableColumn(this.mainTableVo.getTableName());
			}
			if (this.originalMainColumns == null || this.originalMainColumns.size() == 0) {
				this.originalMainColumns = DbReadTableUtil.readOriginalTableColumn(this.mainTableVo.getTableName());
			}
			localHashMap.put("columns", this.mainColums);

			localHashMap.put("originalColumns", this.originalMainColumns);
			for (Iterator<ColumnVo> iterator = this.originalMainColumns.iterator(); iterator.hasNext();) {

				ColumnVo columnVo = iterator.next();
				if (columnVo.getFieldName().toLowerCase().equals(CodeConfigProperties.DB_TABLE_ID.toLowerCase())) {
					localHashMap.put("primaryKeyPolicy", columnVo.getFieldType());
				}
			}
			for (Iterator<SubTableVo> localIterator = this.subTables.iterator(); localIterator.hasNext();) {

				SubTableVo subTableVo = localIterator.next();
				if (subTableVo.getColums() == null || subTableVo.getColums().size() == 0) {

					Object object = DbReadTableUtil.readTableColumn(subTableVo.getTableName());
					subTableVo.setColums((List) object);
				}
				if (subTableVo.getOriginalColumns() == null || subTableVo.getOriginalColumns().size() == 0) {

					List<ColumnVo> list = DbReadTableUtil.readOriginalTableColumn(subTableVo.getTableName());
					subTableVo.setOriginalColumns(list);
				}
				String[] localObject2 = subTableVo.getForeignKeys();
				ArrayList<String> localArrayList = new ArrayList<String>();
				for (String str : localObject2) {
					localArrayList.add(DbReadTableUtil.d(str));
				}
				subTableVo.setForeignKeys(localArrayList.<String>toArray(new String[0]));

				subTableVo.setOriginalForeignKeys(localObject2);
			}
			localHashMap.put("subTables", this.subTables);
		} catch (Exception localException) {

			throw localException;
		}
		long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
		localHashMap.put("serialVersionUID", String.valueOf(serialVersionUID));
		log.info("code template data: " + localHashMap.toString());
		return (Map) localHashMap;
	}

	public void generateCodeFile() throws Exception {
		log.info("---Code----Generation----[一对多模型:" + this.mainTableVo.getTableName() + "]------- 生成中。。。");

		String projectPath = CodeConfigProperties.projectPath;
		Map<String, Object> templateData = initData();

		String templatePath = CodeConfigProperties.templatePath;

		templatePath = templatePath.substring(0, templatePath.lastIndexOf("/") + 1) + "onetomany";
		CreateFileConfig createFileConfig = new CreateFileConfig(templatePath);
		generateFileCommon(createFileConfig, projectPath, templateData);
		log.info("----Code----Generation-----[一对多模型：" + this.mainTableVo.getTableName() + "]------ 生成完成。。。");
	}

	public void generateCodeFile(String projectPath, String templatePath) throws Exception {
		if (projectPath != null && !"".equals(projectPath)) {
			CodeConfigProperties.setProject_path(projectPath);
		}
		if (templatePath != null && !"".equals(templatePath)) {
			CodeConfigProperties.setTemplatePath(templatePath);
		}
		generateCodeFile();
	}
}