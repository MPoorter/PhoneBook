package phonebook.model.domain;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneListing implements Comparable<PhoneListing> {

	private String name;
	private String phoneNumber;
	private String address;

	public PhoneListing() {
		this(null, null, null);
	}

	public PhoneListing(String name, String phoneNumber, String address){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.address=address;
	}

	public static Comparator<PhoneListing> getTownPostCodeComparator() {
		return (p1, p2) -> getTownAndPostCode(p1.getAddress()).compareToIgnoreCase(getTownAndPostCode(p2.getAddress()));
	}

	private static String getTownAndPostCode(String address) {
		String[] addressParts = address.split(",");
		return addressParts[addressParts.length - 2].trim();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phone_number")
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Override
	public int compareTo(final PhoneListing o) {
		String[] nameArrayO = o.getName().split(" ");
		String[] nameArrayThis = this.getName().split(" ");
		int comparing = nameArrayThis[nameArrayThis.length-1].compareToIgnoreCase(nameArrayO[nameArrayO.length-1]);
		if (comparing == 0) {
			comparing = nameArrayThis[0].compareToIgnoreCase(nameArrayO[0]);
		}
		return comparing;
	}
}
