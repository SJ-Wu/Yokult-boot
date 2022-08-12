package tibame.tga102.yokult.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import tibame.tga102.yokult.util.YokultConstants;

public class AuthorizationCheckFilter extends OncePerRequestFilter {

	// JWT implementation referenced from
	// https://ithelp.ithome.com.tw/articles/10272035
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// CORS Option byPass: 
		// Referenced: https://medium.com/@yovan/%E9%82%A3%E4%BA%9B%E7%B6%93%E6%AD%B7%E9%81%8E%E7%9A%84-cors-%E8%A0%A2%E5%95%8F%E9%A1%8C-e63576f67066
		if ("OPTIONS".equals(request.getMethod())) {
			System.out.println("Method OPTION");
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
		} else {
			if ((!request.getServletPath().equals(YokultConstants.MEMBERAPI + "/login"))
					&& (!request.getServletPath().equals(YokultConstants.MEMBERAPI + "/register"))) {
				System.out.println("Need AUTH");
				String authorHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
				String bearer = "Bearer";
				System.out.println(authorHeader);
				if (authorHeader != null && authorHeader.startsWith(bearer)) {
					try {
						String token = authorHeader.substring(bearer.length());
						Claims claims = Jwts.parser().setSigningKey(YokultConstants.JWTKEY).parseClaimsJws(token).getBody();

						System.out.println("JWT payload:" + claims.toString());

						filterChain.doFilter(request, response);

					} catch (Exception e) {
						System.err.println("Error : " + e);
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);

						Map<String, String> err = new HashMap<>();
						err.put("jwt_err", e.getMessage());
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						new ObjectMapper().writeValue(response.getOutputStream(), err);
					}
				} else {
					System.out.println("SC_UNAUTHORIZED");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} else {
				System.out.println("Login path");
				filterChain.doFilter(request, response);
			}
		}
	}
}
