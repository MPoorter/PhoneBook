package phonebook.model.facade;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import phonebook.model.domain.PhoneBook;
import phonebook.model.domain.PhoneListing;
import phonebook.model.domain.repository.PhoneBookRepository;
import phonebook.model.services.PhoneBookRestService;

@Test
public class TestPhoneBookFacadeImpl {

	private PhoneBookFacade facade;
	private PhoneBookRepository repository;
	private PhoneBookRestService service;

	@BeforeMethod
	public void setUp(){
		repository = Mockito.mock(PhoneBookRepository.class);
		service = Mockito.mock(PhoneBookRestService.class);
	}

	public void testSearchPhoneListings(){
		PhoneBook phoneBook = new PhoneBook();
		SortedSet<PhoneListing> phoneListings = new TreeSet<>();
		phoneListings.add(new PhoneListing("Marlene Dietrich", "+447578695", "20 Cabaret Street, London EC4 8BT, UK"));
		phoneListings.add(new PhoneListing("Sylvester Stalone", "+447587523", "4 Rock Street, London EC7 1CT, UK"));
		phoneListings.add(new PhoneListing("F. Scott Fitzgerald", "+44757458", "60 Gatsby Boulevard, London NW2 8AR, UK"));
		phoneBook.setContacts(phoneListings);

		Mockito.when(repository.getPhoneBook()).thenReturn(phoneBook);

		facade = new PhoneBookFacadeImpl(service, repository);

		List<PhoneListing> results = facade.getPhoneListingsSearchResult("Dietrich", null);
		Assert.assertEquals(results.size(), 1);
		Assert.assertEquals(results.get(0).getName(), "Marlene Dietrich");

		results = facade.getPhoneListingsSearchResult("e", null);
		Assert.assertEquals(results.size(), 3);
		Assert.assertTrue(results.containsAll(phoneListings));

		results = facade.getPhoneListingsSearchResult("e", "+447578695");
		Assert.assertEquals(results.size(), 1);
		Assert.assertEquals(results.get(0).getName(), "Marlene Dietrich");

		results = facade.getPhoneListingsSearchResult("Marlene", "5864452");
		Assert.assertTrue(results.isEmpty());
	}
}
