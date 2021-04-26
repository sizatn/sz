package com.sizatn.sz.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	
	/**
	 * 
	 * @param parameters id=1&name=zhenxiang
	 * @return
	 */
	public static Map<String, Object> getParameterMap(String parameters) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (StringUtil.isEmpty(parameters)) {
			return paramsMap;
		}
		while (parameters.startsWith("?")) {
			parameters = parameters.substring(1);
		}
		String[] paramPair = StringUtil.splitByTokenizer(parameters, "&");

		int len = paramPair.length;
		for (int i = 0; i < len; i++) {
			String strParamPair = paramPair[i].trim();
			if (strParamPair.equals("")) {
				continue;
			}
			String[] paramNameVal = StringUtil.splitByTokenizer(strParamPair, "=");
			int len2 = paramNameVal.length;
			if (len2 == 2) {
				paramsMap.put(paramNameVal[0].trim(), new String[] { paramNameVal[1].trim() });
			}
		}
		return paramsMap;
	}

}
