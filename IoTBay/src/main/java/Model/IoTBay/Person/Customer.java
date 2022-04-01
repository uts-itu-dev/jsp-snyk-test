package Model.IoTBay.Person;

import DB.MongoConnection;
import Model.IoTBay.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

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
	
	@Override
	public void addToDatabase() {
		MongoConnection connection = MongoConnection.makeConnection();
		
		Document customer = new Document("_id", userID)
			.append("FirstName", getFirstName())
			.append("LastName", getLastName())
			.append("EmailAddress", getEmail())
			.append("Address", getAddress());

		connection.getCollection().insertOne(customer);
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
