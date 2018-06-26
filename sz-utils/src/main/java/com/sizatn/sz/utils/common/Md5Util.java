package com.sizatn.sz.utils.common;


import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @desc Md5加密
 */
public class Md5Util {

	public static String getMd5(String password) {
		String str = "";
		if (StringUtils.isBlank(password)) {
			return str;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			Base64 base = new Base64();
			// 加密后的字符串
			str = base.encodeToString(md.digest(password.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		String str = "123456";
		String password = Md5Util.getMd5(Md5Util.getMd5(str));
		System.out.println(password);
		System.out.println(password.length());
	}

}