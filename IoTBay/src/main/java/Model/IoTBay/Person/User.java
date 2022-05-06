package Model.IoTBay.Person;

import java.io.Serializable;

/**
 * The base class for a person involved in IoTBay.
 *
 * @author Michael Wu
 */
public class User implements Serializable {
	private static int numberOfUsers = 0;
	
	public final int ID;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	private Address address;
	

	private EUserType type;

	/**
	 * Default Constructor for a user.
	 *
	 * @param fn First Name.
	 * @param ln Last Name.
	 * @param pw Password.
	 * @param em Email.
	 * @param t Type.
	 */
	public User(String fn, String ln, String pw, String em, EUserType t) {
		ID = numberOfUsers++;
		
		firstName = fn;
		lastName = ln;
		password = pw;
		email = em;
		type = t;
	}

	public User(String fn, String ln, String pw, String em, Address add, EUserType t) {
		this(fn, ln, pw, em, t);

		address = add;
	}

	public User(String fn, String ln, String pw, String em, Address add, PaymentInformation pi, EUserType t) {
		this(fn, ln, pw, em, t);

		address = add;
	}
	
	public void addToDatabase() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public EUserType getType() {
		return type;
	}

	public void setFirstName(String v) {
		firstName = v;
	}

	public void setLastName(String v) {
		lastName = v;
	}

	public void setPassword(String v) {
		password = v;
	}

	public void setEmail(String v) {
		email = v;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setType(EUserType ut) {
		type = ut;
	}

	public enum EUserType {
		CUSTOMER,
		STAFF
	}
}
