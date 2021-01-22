package com.sizatn.sz.codegenerate.generate;

import java.util.Map;

public interface IGenerate {
	
	Map<String, Object> initData() throws Exception;

	void generateCodeFile() throws Exception;

	void generateCodeFile(String paramString1, String paramString2) throws Exception;
	
}