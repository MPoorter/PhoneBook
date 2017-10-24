package phonebook.model.facade;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import phonebook.model.domain.PhoneBook;
import phonebook.model.domain.PhoneListing;
import phonebook.model.domain.repository.PhoneBookRepository;
import phonebook.model.services.PhoneBookRestService;

@Component
public class PhoneBookFacadeImpl implements PhoneBookFacade {

	private PhoneBookRestService restService;
	private PhoneBookRepository repository;

	@Autowired
	public PhoneBookFacadeImpl(PhoneBookRestService restService, PhoneBookRepository repository) {
		this.restService = restService;
		this.repository = repository;
	}

	@PostConstruct
	public void loadPhoneBook() {
		PhoneBook phoneBook = restService.getPhoneBook();
		repository.savePhoneBook(phoneBook);
	}

	@Override
	public PhoneBook getPhoneBook() {
		return repository.getPhoneBook();
	}

	@Override
	public List<PhoneListing> getPhoneListingsSearchResult(final String name, final String phone) {
		List<PhoneListing> phoneListings = new ArrayList<>(getPhoneBook().getContacts());
		List<PhoneListing> results = phoneListings.stream().filter(phoneListing -> {
			boolean result = true;
			if (!isBlank(name)) {
				result &= phoneListing.getName().contains(name);
			}
			if (!isBlank(phone)){
				result &= phoneListing.getPhoneNumber().equalsIgnoreCase(phone);
			}
			return result;
		}).collect(Collectors.toList());
		return results;
	}
}
