package DAO;

import Model.IoTBay.Person.*;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database Manager for any data involved in IoTBay.
 *
 * @author Michael Wu
 */
public class DBManager {

	private final Statement statement;
	private final Connection connection;

	public ArrayList<Customer> customers;
	public ArrayList<Staff> staff;
	public ArrayList<Product> products;

	public DBManager(Connection c) throws SQLException {
		connection = c;
		statement = c.createStatement();

		// Read DB Information from a previous execution.
		customers = injectCustomers();
		staff = injectStaff();
		products = injectProducts();

		int cc = customers.size();
		int sc = staff.size();
		int pc = products.size();

		System.out.println("Injected " + cc + " Customers | " + sc + " Staff | " + pc + " Products into IoTBay.");
	}

	public Customer findCustomer(String email) throws SQLException {
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS WHERE EMAIL='" + email + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next()) {
			if (r.getString(1).equals(email)) {
				return resultSetToCustomer(r);
			}
		}

		return null;
	}

	public Staff findStaff(String email) throws SQLException {
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.STAFF WHERE EMAIL='" + email + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next()) {
			if (r.getString(1).equals(email)) {
				return resultSetToStaff(r);
			}
		}

		return null;
	}

	public Product findProduct(int id) throws SQLException {
		String instruction = "SELECT * FROM IOTBAY.PRODUCTS WHERE PRODUCTID=" + id;
		ResultSet r = statement.executeQuery(instruction);

		while (r.next()) {
			System.out.println(r.getInt(1));
			if (r.getInt(1) == id) {
				return resultSetToProduct(r);
			}
		}

		return null;
	}

	public Customer findCustomer(String email, String password) throws SQLException {
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next()) {
			if (r.getString(1).equals(email) && r.getString(4).equals(password)) {
				return resultSetToCustomer(r);
			}
		}

		return null;
	}

	public Staff findStaff(String email, String password) throws SQLException {
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.STAFF WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next()) {
			if (r.getString(1).equals(email) && r.getString(4).equals(password)) {
				return resultSetToStaff(r);
			}
		}

		return null;
	}

	public void add(Customer c) throws SQLException {
		Address a = c.getAddress();

		final String attributes = " (FIRSTNAME, LASTNAME, PASSWORD, EMAIL, STREETNUMBER, STREETNAME, SUBURB, POSTCODE, CITY, PHONENUMBER) ";

		String instruction = "INSERT INTO IOTBAY.CUSTOMERS" + attributes + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, c.getFirstName());
		ps.setString(2, c.getLastName());
		ps.setString(3, c.getPassword());
		ps.setString(4, c.getEmail().toLowerCase());
		ps.setString(5, a.getNumber());
		ps.setString(6, a.getStreetName());
		ps.setString(7, a.getSuburb());
		ps.setString(8, a.getPostcode());
		ps.setString(9, a.getCity());
		ps.setString(10, c.getPhoneNumber());

		ps.execute();

		System.out.println("End Exec.\nAdded new Customer " + c.getFirstName() + " " + c.getEmail());
	}

	public void add(Staff s) throws SQLException {
		final String attributes = " (FIRSTNAME, LASTNAME, PASSWORD, EMAIL) ";
		String instruction = "INSERT INTO IOTBAY.STAFF " + attributes + "VALUES (?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, s.getFirstName());
		ps.setString(2, s.getLastName());
		ps.setString(3, s.getPassword());
		ps.setString(4, s.getEmail().toLowerCase());

		ps.execute();
	}

	public void add(Product p) throws SQLException {
		final String attributes = " (PRODUCTNAME, DESCRIPTION, PRICE) ";
		String instruction = "INSERT INTO IOTBAY.PRODUCTS " + attributes + "VALUES (?, ?, ?)";

		// Something fancy with the SQL command which allows apostrophes ' to be added
		// to the Description.
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, p.getName());
		ps.setString(2, p.getDescription());
		ps.setFloat(3, p.getPrice());

		ps.execute();
	}

	public void update(Customer c, String fn, String ln, String pw, String email, String phone, String addNum, String addStreetName, String addSuburb, String addPostcode, String addCity, String cardNo, String cvv, String cardHolder) throws SQLException {
		String instruction = "UPDATE IOTBAY.CUSTOMERS SET FIRSTNAME=?, LASTNAME=?, PASSWORD=?, EMAIL=?, PHONENUMBER=?, STREETNUMBER=?, STREETNAME=?, SUBURB=?, POSTCODE=?, CITY=?, CARDNUMBER=?, CVV=?, CARDHOLDER=? WHERE EMAIL=?";
		
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, fn);
		ps.setString(2, ln);
		ps.setString(3, pw);
		ps.setString(4, email);
		ps.setString(5, phone);
		ps.setString(6, addNum);
		ps.setString(7, addStreetName);
		ps.setString(8, addSuburb);
		ps.setString(9, addPostcode);
		ps.setString(10, addCity);
		ps.setString(11, cardNo);
		ps.setString(12, cvv);
		ps.setString(13, cardHolder);
		ps.setString(14, c.getEmail());

		ps.executeUpdate();
	}

	public void update(Staff s, String fn, String ln, String pw, String email) throws SQLException {
		String instruction = "UPDATE IOTBAY.STAFF SET FIRSTNAME=?, LASTNAME=?, PASSWORD=?, EMAIL=? WHERE EMAIL=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, fn);
		ps.setString(2, ln);
		ps.setString(3, pw);
		ps.setString(4, email);
		ps.setString(5, s.getEmail());
		
		ps.executeUpdate();
	}

	public void updateProduct(int id, String name, String desc, String price) throws SQLException {
		String instruction = "UPDATE IOTBAY.PRODUCTS SET PRODUCTNAME=?, DESCRIPTION=?, PRICE=? WHERE PRODUCTID=?";
		
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, name);
		ps.setString(2, desc);
		ps.setFloat(3, Float.parseFloat(price));
		ps.setInt(4, id);		

		ps.executeUpdate();
	}

	// Obsolete.
	//private String concatValues(String... s) {
		//final String concat = "', '";
		//String ret = "VALUES ('";

		//for (int i = 0; i < s.length; ++i) {
			//System.out.println(s[i]);
			//ret += s[i];

			//if (i != s.length - 1) {
				//ret += concat;
			//}
		//}

		//System.out.println(ret);

		//return ret + "')";
	//}

	public final ArrayList<Customer> injectCustomers() throws SQLException {
		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Customer> dbInjected = new ArrayList();

		while (r.next()) {

			// Inject.
			dbInjected.add(resultSetToCustomer(r));
		}

		return dbInjected;
	}

	public final ArrayList<Staff> injectStaff() throws SQLException {
		String instruction = "SELECT * FROM IOTBAY.STAFF";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Staff> dbInjected = new ArrayList();

		while (r.next()) {
			// Inject.
			dbInjected.add(resultSetToStaff(r));
		}

		return dbInjected;
	}

	public final ArrayList<Product> injectProducts() throws SQLException {
		String instruction = "SELECT * FROM IOTBAY.PRODUCTS";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Product> dbInjected = new ArrayList();

		while (r.next()) {
			// Inject.
			dbInjected.add(resultSetToProduct(r));
		}

		return dbInjected;
	}

	private Customer resultSetToCustomer(ResultSet r) throws SQLException {

		// Personal Information.
		String em = r.getString(1);
		String fn = r.getString(2);
		String ln = r.getString(3);
		String pw = r.getString(4);

		// Address Information.
		String sNo = r.getString(5);
		String sNa = r.getString(6);
		String sub = r.getString(7);
		String psc = r.getString(8);
		String cit = r.getString(9);

		// Phone Number.
		String pn = r.getString(10);

		// Payment Information.
		String cNo = r.getString(11);
		String cvv = r.getString(12);
		String cHo = r.getString(13);

		// Construct.
		Address a = new Address(sNo, sNa, sub, psc, cit);
		PaymentInformation p = new PaymentInformation(cNo, cvv, cHo);

		return new Customer(fn, ln, pw, em, a, pn, p);
	}

	private Staff resultSetToStaff(ResultSet r) throws SQLException {

		// Personal Information.
		String em = r.getString(1);
		String fn = r.getString(2);
		String ln = r.getString(3);
		String pw = r.getString(4);

		return new Staff(fn, ln, pw, em);
	}

	private Product resultSetToProduct(ResultSet r) throws SQLException {

		// Product Information.
		String pname = r.getString(2);
		String descr = r.getString(3);
		float fPrice = r.getFloat(4);

		return new Product(pname, descr, fPrice);
	}
}
