package tibame.tga102.yokult;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import tibame.tga102.yokult.filter.AdminAuthorizationCheckFilter;
import tibame.tga102.yokult.filter.ClinicAuthorizationCheckFilter;
import tibame.tga102.yokult.util.YokultConstants;

@Configuration
public class WebSecurityConfig {
	
	@Bean
    public SecurityFilterChain clinicFilterChain(HttpSecurity http) throws Exception {
		http.antMatcher(YokultConstants.MEMBER_API)	// 關掉csrf保護
		.csrf().disable()
		.addFilterBefore(new ClinicAuthorizationCheckFilter(), BasicAuthenticationFilter.class);
            
        return http.build();
    }

	@Bean
	public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
		http.antMatcher(YokultConstants.MEMBER_ADMIN_API)	// 關掉csrf保護
		.csrf().disable()
		.addFilterBefore(new AdminAuthorizationCheckFilter(), BasicAuthenticationFilter.class);
        return http.build();
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
