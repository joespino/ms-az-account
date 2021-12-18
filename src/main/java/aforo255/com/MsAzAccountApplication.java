package aforo255.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import aforo255.com.config.TopicProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(TopicProperties.class)
public class MsAzAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAzAccountApplication.class, args);
	}

}
