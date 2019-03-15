package priv.dengjl.util;

public class Test {

	public static void main(String[] args) {
		/*String generateToken = JwtTokenUtil.generateToken(new JwtInfo("aaaa"), 10);
		System.out.println(generateToken);*/
		
		String generateToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOnsidWlkIjoiYWFhYSJ9LCJleHAiOjE1NTI2MzY5ODN9.ZM2OPHNPbvKeTwmuNcTgGPkeHx3-ubOltdI5QlpjoMQ";
		
		JwtInfo tokenInfo = JwtTokenUtil.getTokenInfo(generateToken);
		
		System.out.println(tokenInfo);
		
		// 超时异常 ExpiredJwtException
		
		// 签名异常 SignatureException
	}

}
