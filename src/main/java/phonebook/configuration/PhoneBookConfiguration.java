package phonebook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PhoneBookConfiguration {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propConfig(){
		return new PropertySourcesPlaceholderConfigurer();
	}
}
