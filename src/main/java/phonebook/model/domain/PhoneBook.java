package phonebook.model.domain;

import java.util.SortedSet;

public class PhoneBook {

	private SortedSet<PhoneListing> contacts;

	public SortedSet<PhoneListing> getContacts() {
		return contacts;
	}

	public void setContacts(final SortedSet<PhoneListing> contacts) {
		this.contacts = contacts;
	}
}
