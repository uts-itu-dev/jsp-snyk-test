package Model.IoTBay.Person;

/**
 * An Address defined by number, street name, suburb, postcode, state and
 * country.
 *
 * @author Michael Wu
 */
public class Address {

	private int number;
	private String streetName;
	private String suburb;
	private int postcode;
	private String state;
	private String country;

	public Address(int number, String streetName, String suburb, int postcode, String state, String country) {
		this.number = number;
		this.streetName = streetName;
		this.suburb = suburb;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
	}

	public int getNumber() {
		return number;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public int getPostcode() {
		return postcode;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
