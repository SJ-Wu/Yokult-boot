package tibame.tga102.yokult.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
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

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		boolean loginPath = (YokultConstants.MEMBER_API + "/login").equals(path);
		boolean registerPath = (YokultConstants.MEMBER_API + "/register").equals(path);
		boolean adminLoginPath = (YokultConstants.STAFF_API + "/login").equals(path);
		return (loginPath || registerPath || adminLoginPath);
	}

	// JWT implementation referenced from
	// https://ithelp.ithome.com.tw/articles/10272035
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

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
	}
}
