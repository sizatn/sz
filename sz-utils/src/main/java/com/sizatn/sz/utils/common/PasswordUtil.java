package com.sizatn.sz.utils.common;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {

	public static String encrypt(String password, String salt) {
		return new SimpleHash("MD5", password.toCharArray(), salt, 2).toString();
	}

}
