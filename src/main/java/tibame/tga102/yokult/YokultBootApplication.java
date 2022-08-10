package tibame.tga102.yokult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories
public class YokultBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(YokultBootApplication.class, args);
	}

}
