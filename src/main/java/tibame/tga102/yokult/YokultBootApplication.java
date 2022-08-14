package tibame.tga102.yokult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@EnableMongoRepositories
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class YokultBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(YokultBootApplication.class, args);
	}

}
