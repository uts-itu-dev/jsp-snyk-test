package Model.IoTBay.Person;

import Model.IoTBay.Product;
import java.io.Serializable;

/**
 * A Member of Staff of IoTBay.
 *
 * This could be Staff (in general), Product Manager or System Manager.
 *
 * @author Michael Wu
 */
public class Staff extends User implements Serializable {

	private String role;

	public Staff(String fn, String ln, String pw, String em) {
		super(fn, ln, pw, em);
		
	}
	
	@Override
	public void addToDatabase() {
		
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
