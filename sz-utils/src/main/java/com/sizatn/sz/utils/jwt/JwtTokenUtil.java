package com.sizatn.sz.utils.jwt;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

public class JwtTokenUtil {

	/**
	 * @param token
	 * @param claimsMap
	 * @return 是否正确
	 * @desc 校验token是否正确
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	@SuppressWarnings("unused")
	public static boolean verify(String token, Map<String, Object> claimsMap) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(claimsMap.get("secret").toString());
			Verification verification = JWT.require(algorithm);
			for (Entry<String, Object> entry : claimsMap.entrySet()) {
				verification.withClaim(entry.getKey(), entry.getValue().toString());
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
	 * @return token中包含的claim信息
	 * @desc 获得token中的信息无需secret解密也能获得
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static Map<String, Claim> getClaims(String token) {
		try {
			return JWT.decode(token).getClaims();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * @param secret 密钥
	 * @param claimsMap 用户信息
	 * @param expireTime 过期时间
	 * @return 加密的token
	 * @desc 生成签名
	 * @author sizatn
	 * @date Jun 26, 2018
	 */
	public static String create(String secret, Map<String, Object> claimsMap, long expireTime) {
		try {
			Date date = new Date(System.currentTimeMillis() + expireTime);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			Builder builder = JWT.create();
			for (Entry<String, Object> entry : claimsMap.entrySet()) {
				builder.withClaim(entry.getKey(), entry.getValue().toString());
			}
			return builder.withExpiresAt(date).sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

}
