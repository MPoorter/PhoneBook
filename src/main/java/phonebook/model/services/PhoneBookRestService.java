package phonebook.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import phonebook.model.domain.PhoneBook;

@RestController
public class PhoneBookRestService {

	private RestTemplate restTemplate;

	@Autowired
	public PhoneBookRestService(RestTemplate restTemplate){
		this.restTemplate=restTemplate;
	}

	@Value("${phonebook.url}")
	private String baseURL;

	public PhoneBook getPhoneBook() {
		PhoneBook phoneBook = restTemplate.getForObject(baseURL, PhoneBook.class);
		return phoneBook;
	}
}
