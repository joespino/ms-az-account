package aforo255.com.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "config")
public class TopicProperties {

	private String connectionString ;
	private String topicName;
	private String subscriptionName;

}
