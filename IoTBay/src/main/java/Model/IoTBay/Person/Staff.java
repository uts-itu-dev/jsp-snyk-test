package Model.IoTBay.Person;

import DB.MongoConnection;
import Model.IoTBay.Product;
import java.io.Serializable;
import org.bson.Document;

/**
 * A Member of Staff of IoTBay.
 *
 * This could be Staff (in general), Product Manager or System Manager.
 *
 * @author Michael Wu
 */
public class Staff extends User implements Serializable {

	private String role;

	public Staff(String fn, String ln, String pw, String em, Address add, String role) {
		super(fn, ln, pw, em, add);

		this.role = role;
	}
	
	@Override
	public void addToDatabase() {
		MongoConnection connection = MongoConnection.makeConnection();
		
		Document staff = new Document("_id", userID)
			.append("FirstName", getFirstName())
			.append("LastName", getLastName())
			.append("EmailAddress", getEmail())
			.append("Address", getAddress());

		connection.getCollection().insertOne(staff);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String v) {
		role = v;
	}
	
	public Product addProduct(String name, String desc, float price) {
		Product p = new Product(name, desc, price);
		
		// ...
		
		
		return p;
	}
}
