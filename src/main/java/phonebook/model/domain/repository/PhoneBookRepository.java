package phonebook.model.domain.repository;

import phonebook.model.domain.PhoneBook;

public interface PhoneBookRepository {

	void savePhoneBook(PhoneBook phoneBook);

	PhoneBook getPhoneBook();
}
