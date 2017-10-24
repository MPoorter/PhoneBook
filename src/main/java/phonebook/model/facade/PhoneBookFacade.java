package phonebook.model.facade;

import java.util.List;

import phonebook.model.domain.PhoneBook;
import phonebook.model.domain.PhoneListing;

public interface PhoneBookFacade {

	PhoneBook getPhoneBook();

	List<PhoneListing> getPhoneListingsSearchResult(String name, String phone);
}
