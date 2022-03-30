package Model.IoTBay.Person;

import java.io.Serializable;

/**
 * A Registered Customer of IoTBay.
 *
 * @author Michael Wu
 */
public class Customer extends User implements Serializable {

	private String phoneNumber;

	public Customer(String fn, String ln, String pw, String em, Address add, String pn) {
		super(fn, ln, pw, em, add);

		phoneNumber = pn;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String v) {
		phoneNumber = v;
	}
}
