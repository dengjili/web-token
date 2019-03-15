package priv.dengjl.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.joda.time.DateTime;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtTokenUtil {

	// key
	private static SecretKeySpec getSecretKeyInstance() {
		byte[] keyBytes = DatatypeConverter.parseBase64Binary("xxxxxyyyyy");
		return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
	}

	// 加密
	public static String generateToken(JwtInfo jwtInfo, int expire) {
		return Jwts.builder().claim(JwtConstant.JWT_KEY_USER_ID, jwtInfo)
				.setExpiration(DateTime.now().plusSeconds(expire).toDate())
				.signWith(SignatureAlgorithm.HS256, getSecretKeyInstance()).compact();
	}

	// 解密
	public static JwtInfo getTokenInfo(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getSecretKeyInstance()).parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return new JwtInfo(body.get(JwtConstant.JWT_KEY_USER_ID).toString());
	}

	// 校验合法性
	public static boolean isValidate(String token) {
		try {
			JwtTokenUtil.getTokenInfo(token);
		} catch (ExpiredJwtException exception) {
			return false;
		} catch (SignatureException exception2) {
			return false;
		}
		return true;
	}
}
