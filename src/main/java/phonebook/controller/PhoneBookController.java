package phonebook.controller;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import phonebook.model.domain.PhoneListing;
import phonebook.model.facade.PhoneBookFacade;

@Controller
@Scope("session")
@RequestMapping("/PhoneBook")
public class PhoneBookController {

	private PhoneBookFacade facade;

	@Autowired
	public PhoneBookController(PhoneBookFacade facade) {
		this.facade = facade;
	}

	@GetMapping
	public String handleGetPhoneBook(ModelMap map) {
		map.addAttribute("phonebookListings", facade.getPhoneBook().getContacts());
		map.addAttribute("sorting", "Sort by town");
		return "phonebook";
	}

	@PostMapping (params="sort")
	public String handlePostPhoneBook(ModelMap map, @RequestParam("sort") String sort) {
		if (sort.equals("Sort by town")) {
			List<PhoneListing> phoneListings = new ArrayList<>(facade.getPhoneBook().getContacts());
			phoneListings.sort(PhoneListing.getTownPostCodeComparator());
			map.addAttribute("phonebookListings", phoneListings);
			map.addAttribute("sorting", "Sort by family name");
			return "phonebook";
		} else {
			return handleGetPhoneBook(map);
		}
	}

	@PostMapping(params = "search")
	public String handleSearchPhoneBook(ModelMap map, @RequestParam("searchName") String searchName, @RequestParam("searchPhone") String searchPhone) {
		map.addAttribute("sorting", "Clear search results");
		if (isBlank(searchName.trim()) && isBlank(searchPhone.trim())) {
			map.addAttribute("error", "Please fill in one or both fields");
			return "phonebook";
		} else {
			List<PhoneListing> results = facade.getPhoneListingsSearchResult(searchName.trim(), searchPhone.trim());
			Collections.sort(results);
			map.addAttribute("phonebookListings", results);
			return "phonebook";
		}
	}
}
