package tibame.tga102.yokult.jwt;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tibame.tga102.yokult.util.YokultConstants;

public class JwtToken {
	public static String generate(String subject, Integer minutes) {
		Date expireDate = new Date(System.currentTimeMillis() + minutes * 60 * 1000);
		return Jwts.builder().setSubject(subject).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, YokultConstants.JWTKEY).compact();
	}
}
