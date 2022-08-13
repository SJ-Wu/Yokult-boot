package tibame.tga102.yokult;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import tibame.tga102.yokult.filter.AdminAuthorizationCheckFilter;
import tibame.tga102.yokult.filter.ClinicAuthorizationCheckFilter;
import tibame.tga102.yokult.util.YokultConstants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Configuration
	@Order(2)
	static class clinicWebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher(YokultConstants.MEMBER_API)
			// 關掉csrf保護
			.csrf().disable();
			// 不寫session了
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			// 加上剛剛寫的filter
			http.addFilterBefore(new ClinicAuthorizationCheckFilter(), BasicAuthenticationFilter.class);
			System.out.println("clinicWebSecurityConfig setting");
		}
	}
	
	@Configuration
	@Order(1)
	static class adminWebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher(YokultConstants.MEMBER_ADMIN_API);
			// 關掉csrf保護
			http.csrf().disable();
			// 不寫session了
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			// 加上剛剛寫的filter
			http.addFilterBefore(new AdminAuthorizationCheckFilter(), BasicAuthenticationFilter.class);
			System.out.println("adminWebSecurityConfig setting");
		}
	}

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
