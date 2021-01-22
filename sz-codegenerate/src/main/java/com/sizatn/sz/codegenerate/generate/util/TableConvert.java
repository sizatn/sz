package com.sizatn.sz.codegenerate.generate.util;

import org.apache.commons.lang3.StringUtils;

public class TableConvert {
	public static String getNullAble(String paramString) {
		if ("YES".equals(paramString) || "yes".equals(paramString) || "y".equals(paramString) || "Y".equals(paramString)
				|| "f".equals(paramString)) {
			return "Y";
		}
		if ("NO".equals(paramString) || "N".equals(paramString) || "no".equals(paramString) || "n".equals(paramString)
				|| "t".equals(paramString)) {
			return "N";
		}
		return null;
	}

	public static String getNullString(String paramString) {
		if (StringUtils.isBlank(paramString)) {
			return "";
		}
		return paramString;
	}

	public static String getV(String paramString) {
		return "'" + paramString + "'";
	}
}
