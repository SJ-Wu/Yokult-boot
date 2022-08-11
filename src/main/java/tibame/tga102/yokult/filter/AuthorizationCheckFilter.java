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

public class AuthorizationCheckFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if ((!request.getServletPath().equals("/api/0.02/member/login")) && (!request.getServletPath().equals("/api/0.02/member/register"))) {
			System.out.println("Need AUTH");
			String authorHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			String bearer = "Bearer";
			if(authorHeader!= null && authorHeader.startsWith(bearer)){
                try{
                String token = authorHeader.substring(bearer.length());
                Claims claims = Jwts.parser().setSigningKey("tga102yokult")
                .parseClaimsJws(token).getBody();

                System.out.println("JWT payload:"+claims.toString());

                filterChain.doFilter(request, response);
                
                }catch(Exception e){
                    System.err.println("Error : "+e);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    
                    Map<String, String> err = new HashMap<>();
                    err.put("jwt_err", e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), err);
                }
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
		} else {
			System.out.println("Login path");
			filterChain.doFilter(request, response);
		}
	}
}
