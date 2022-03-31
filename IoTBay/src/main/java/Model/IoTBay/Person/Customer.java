package Model.IoTBay.Person;

import Model.IoTBay.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * A Registered Customer of IoTBay.
 *
 * @author Michael Wu
 */
public class Customer extends User implements Serializable {

	private String phoneNumber;
	private Date DOB;
	private ArrayList<Order> purchaseHistory;

	public Customer(String fn, String ln, String pw, String em, Address add, String pn) {
		super(fn, ln, pw, em, add);

		phoneNumber = pn;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public ArrayList<Order> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPhoneNumber(String v) {
		phoneNumber = v;
	}
	
	public void addOrder(Order o) {
		purchaseHistory.add(o);
	}
}
