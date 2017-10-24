package phonebook.model.domain.repository;

import org.springframework.stereotype.Repository;

import phonebook.model.domain.PhoneBook;

@Repository
public class PhoneBookRepositoryImpl implements PhoneBookRepository {

	private PhoneBook phoneBook;

	@Override
	public void savePhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	@Override
	public PhoneBook getPhoneBook() {
		return this.phoneBook;
	}
}
