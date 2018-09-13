package com.sizatn.sz.utils.jwt;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.google.common.base.Strings;

public class JwtTokenUtil {
	
	// 默认密钥，如果传入为空，则用默认值
	private static final String TOKEN_KEY = "aWLT72xAWRFBzCzn"; 
	
	// 默认过期时间，如果传入小于等于0，则用默认值
	private static final long DEFAULT_TIME  = 30 * 60 * 1000;

	/**
	 * @param token
	 * @param secret 密钥
	 * @param claimsMap 用户信息
	 * @return 是否正确
	 * @desc 校验token是否正确
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	@SuppressWarnings("unused")
	public static boolean verify(String token, String secret, Map<String, String> claimsMap) {
		try {
			if (Strings.isNullOrEmpty(secret)) {
				secret = TOKEN_KEY;
			}
			Algorithm algorithm = Algorithm.HMAC256(secret);
			Verification verification = JWT.require(algorithm);
			for (Entry<String, String> entry : claimsMap.entrySet()) {
				verification.withClaim(entry.getKey(), entry.getValue());
			}
			JWTVerifier verifier = verification.build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param token
	 * @param str 要获取的信息
	 * @return token中包含的claim信息
	 * @desc 获得token中的信息无需secret解密也能获得
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static String getInfoFromToken(String token, String str) {
		try {
			return JWT.decode(token).getClaim(str).asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * @param secret 密钥
	 * @param claimsMap 用户信息
	 * @param expireTime 过期为半小时时间，如果采用默认时间则传入小于等于0的数
	 * @return 加密的token
	 * @desc 生成签名
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static String sign(String secret, Map<String, String> claimsMap, long expireTime) {
		try {
			if (Strings.isNullOrEmpty(secret)) {
				secret = TOKEN_KEY;
			}
			if (expireTime <= 0) {
				expireTime = DEFAULT_TIME;
			}
			Date date = new Date(System.currentTimeMillis() + expireTime);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			Builder builder = JWT.create();
			for (Entry<String, String> entry : claimsMap.entrySet()) {
				builder.withClaim(entry.getKey(), entry.getValue());
			}
			return builder.withExpiresAt(date).sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

}
