package DAO;

import Model.IoTBay.Order;
import Model.IoTBay.OrderLineItem;
import Model.IoTBay.Person.*;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Database Manager for any data involved in IoTBay.
 *
 * @author Michael Wu
 */
public class DBManager
{

	/**
	 * Used when a Customer is Anonymous and not registered with IoTBay.
	 * <br><br>
	 * Use this for consistency when handling sensitive information and
	 * differentiating between Registered and Unregistered Customers.
	 */
	public static final String AnonymousUserEmail = "ANONYMOUS@USER.UTS";

	private final Statement statement;
	private final Connection connection;

	public ArrayList<Customer> customers;
	public ArrayList<Staff> staff;
	public ArrayList<Product> products;
	public ArrayList<OrderLineItem> cart;
	public ArrayList<Order> orders;

	public DBManager(Connection c) throws SQLException
	{
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

	/**
	 * Finds a Customer with an Email.
	 *
	 * @param email The Email address to search.
	 * @return Customer with Email email.
	 * @throws SQLException
	 */
	public Customer findCustomer(String email) throws SQLException
	{
		email = email.toLowerCase();

		if (email.equals(AnonymousUserEmail.toLowerCase()))
		{
			return null;
		}

		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS WHERE EMAIL='" + email + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next())
		{
			if (r.getString(1).equals(email))
			{
				return resultSetToCustomer(r);
			}
		}

		return null;
	}

	/**
	 * Finds Staff with an Email.
	 *
	 * @param email The Email address to search.
	 * @return Staff with Email email.
	 * @throws SQLException
	 */
	public Staff findStaff(String email) throws SQLException
	{
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.STAFF WHERE EMAIL='" + email + "'";
		ResultSet r = statement.executeQuery(instruction);

		while (r.next())
		{
			if (r.getString(1).equals(email))
			{
				return resultSetToStaff(r);
			}
		}

		return null;
	}

	/**
	 * Finds a Product by ID.
	 *
	 * @param id The Primary Key of the Product.
	 * @return The Product associated with id.
	 * @throws SQLException
	 */
	public Product findProduct(int id) throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.PRODUCTS WHERE PRODUCTID=" + id;
		ResultSet r = statement.executeQuery(instruction);

		while (r.next())
		{
			if (r.getInt(1) == id)
			{
				return resultSetToProduct(r);
			}
		}

		return null;
	}

	/**
	 * Finds the ID of a Product, given the full details of that Product.
	 *
	 * @param p The full details of the Product to find the corresponding ID.
	 * @return The Primary Key of p.
	 * @throws SQLException
	 */
	public int findProductID(Product p) throws SQLException
	{
		String instruction = "SELECT PRODUCTID FROM IOTBAY.PRODUCTS WHERE PRODUCTNAME=?";
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, p.getName());

		int resultingID = -1;

		ResultSet r = ps.executeQuery();
		while (r.next())
		{
			resultingID = r.getInt(1);
		}

		return resultingID;
	}

	/**
	 * Finds a Customer by login credentials.
	 *
	 * @param email Login Email address.
	 * @param password Login Password.
	 * @return A Customer with the given email and password. Null otherwise.
	 * @throws SQLException
	 */
	public Customer findCustomer(String email, String password) throws SQLException
	{
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS WHERE EMAIL=? AND PASSWORD=?";
		
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet r = ps.executeQuery();

		while (r.next())
		{
			if (r.getString(1).equals(email) && r.getString(4).equals(password))
			{
				return resultSetToCustomer(r);
			}
		}

		return null;
	}

	/**
	 * Finds Staff by login credentials.
	 *
	 * @param email Login Email address.
	 * @param password Login Password.
	 * @return A Staff with the given email and password. Null otherwise.
	 * @throws SQLException
	 */
	public Staff findStaff(String email, String password) throws SQLException
	{
		email = email.toLowerCase();
		String instruction = "SELECT * FROM IOTBAY.STAFF WHERE EMAIL=? AND PASSWORD=?";
		
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet r = ps.executeQuery();

		while (r.next())
		{
			if (r.getString(1).equals(email) && r.getString(4).equals(password))
			{
				return resultSetToStaff(r);
			}
		}

		return null;
	}

	/**
	 * Finds an Order with an ID and owning Email address.
	 *
	 * @param oid The ID of the Order.
	 * @param owner The Email associated with this Order.
	 * @return The Order with the given oid and owner.
	 * @throws SQLException
	 */
	public Order findOrder(int oid, String owner) throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.ORDERS WHERE ORDERID=? AND OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setInt(1, oid);
		ps.setString(2, owner);
		ResultSet r = ps.executeQuery();
		return resultSetToOrder(r, findCustomer(owner));
	}

	/**
	 * Adds a Customer and Registers them in IoTBay.
	 *
	 * @param c The new Customer.
	 * @throws SQLException
	 */
	public void add(Customer c) throws SQLException
	{
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

	/**
	 * Adds a member of Staff to manage IoTBay.
	 *
	 * @param s The new Staff.
	 * @throws SQLException
	 */
	public void add(Staff s) throws SQLException
	{
		final String attributes = " (FIRSTNAME, LASTNAME, PASSWORD, EMAIL) ";
		String instruction = "INSERT INTO IOTBAY.STAFF " + attributes + "VALUES (?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, s.getFirstName());
		ps.setString(2, s.getLastName());
		ps.setString(3, s.getPassword());
		ps.setString(4, s.getEmail().toLowerCase());

		ps.execute();
	}

	/**
	 * Adds a new Product for listing in IoTBay.
	 *
	 * @param p The new Product.
	 * @throws SQLException
	 */
	public void add(Product p) throws SQLException
	{
		final String attributes = " (PRODUCTNAME, DESCRIPTION, PRICE, STOCK) ";
		String instruction = "INSERT INTO IOTBAY.PRODUCTS " + attributes + "VALUES (?, ?, ?, ?)";

		// Something fancy with the SQL command which allows apostrophes ' to be added
		// to the Description.
		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, p.getName());
		ps.setString(2, p.getDescription());
		ps.setFloat(3, p.getPrice());
		ps.setFloat(4, p.getQuantity());

		ps.execute();
	}

	/**
	 * Adds quantity number of Products pid to owner.
	 *
	 * @param pid The Product ID Primary Key of the Product to add.
	 * @param owner The Customer who owns the Cart. Cannot be null. See addToCartAnonymous().
	 * @param quantity The desired number of this Product.
	 * @throws SQLException
	 */
	public void addToCart(int pid, Customer owner, int quantity) throws SQLException
	{
		if (preventDuplicates(pid, owner.getEmail().toLowerCase()))
		{
			return;
		}

		final String attributes = " (OWNER, PRODUCTID, QUANTITY) ";
		String instruction = "INSERT INTO IOTBAY.ORDERLINEITEM " + attributes + " VALUES (?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, owner.getEmail());
		ps.setInt(2, pid);
		ps.setInt(3, quantity);

		ps.execute();
	}

	/**
	 * Adds quantity number of Products pid to an Anonymous Customer.
	 *
	 * @param pid The Product ID Primary Key of the Product to add.
	 * @param quantity The desired number of this Product.
	 * @throws SQLException
	 */
	public void addToCartAnonymous(int pid, int quantity) throws SQLException
	{

		if (preventDuplicates(pid, AnonymousUserEmail))
		{
			return;
		}

		final String attributes = " (PRODUCTID, OWNER, QUANTITY) ";

		String instruction = "INSERT INTO IOTBAY.ORDERLINEITEM " + attributes + " VALUES (?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setInt(1, pid);
		ps.setString(2, AnonymousUserEmail);
		ps.setInt(3, quantity);

		ps.execute();

		injectOLI(AnonymousUserEmail);
	}

	private boolean preventDuplicates(int pid, String owner) throws SQLException
	{
		final String avoidDuplicatesInstruction = "SELECT * FROM IOTBAY.ORDERLINEITEM WHERE PRODUCTID=? AND OWNER=?";
		PreparedStatement rsADI = connection.prepareStatement(avoidDuplicatesInstruction);
		rsADI.setInt(1, pid);
		rsADI.setString(2, owner);

		Product p = findProduct(pid);

		ResultSet existing = rsADI.executeQuery();

		// If something of pid already exists.
		if (existing.next())
		{
			// The Quantity of Product p that already exists in the Cart.
			int currentQuantity = existing.getInt(3);

			// Only update the Quantity of p in the Cart if it doesn't exceed the existing Stock.
			updateCart(pid, owner, (currentQuantity + 1) > p.getQuantity()
				? currentQuantity
				: currentQuantity + 1
			);

			injectOLI(owner);
			return true;
		}

		return false;
	}

	/**
	 * Makes an Order.
	 *
	 * @param owner The Email address of the Customer making the Order.
	 * @param inCart The Cart that will be marked as being Ordered.
	 * @param address The delivery address of this Order.
	 * @param pi The payment information of this Order.
	 * @throws SQLException
	 */
	public void makeOrder(String owner, ArrayList<OrderLineItem> inCart, Address address, PaymentInformation pi) throws SQLException
	{
		float totalCost = 0;

		// Convert to a long String.
		String pids, quas;
		pids = quas = "";

		// Construct our Array Hack.
		// This means to convert an array of integers to a long String
		//	where the array elements are distinguished between colons.
		for (int i = 0; i < inCart.size(); ++i)
		{
			OrderLineItem o = inCart.get(i);
			pids += Integer.toString(findProductID(o.getProduct())) + ":";
			quas += Integer.toString(o.getQuantity()) + ":";

			totalCost += o.getTotalCost();

			// Subtract from Stock/Quantity.
			Product p = o.getProduct();
			updateProduct(findProductID(p), p.getName(), p.getDescription(), p.getPrice() + "", p.getQuantity() - o.getQuantity());
		}

		// Get the time of creating this Order.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String purchaseDate = dtf.format(now);

		String addressNum = address.getNumber();
		String addressStreetName = address.getStreetName();
		String addressSuburb = address.getSuburb();
		String addressPostcode = address.getPostcode();
		String addressCity = address.getCity();

		String cardNo = pi.getCardNo();
		String CVV = pi.getCVV();
		String cardHolder = pi.getCardHolder();

		final String attributes = " (OWNER, PRICE, STATUS, PRODUCTS, QUANTITY, PURCHASEDATE, STREETNUMBER, STREETNAME, SUBURB, POSTCODE, CITY, CARDNUMBER, CVV, CARDHOLDER) ";
		final String instruction = "INSERT INTO IOTBAY.ORDERS " + attributes + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, owner);
		ps.setFloat(2, totalCost);
		ps.setString(3, "Confirmed");
		ps.setString(4, pids);
		ps.setString(5, quas);
		ps.setString(6, purchaseDate);
		ps.setString(7, addressNum);
		ps.setString(8, addressStreetName);
		ps.setString(9, addressSuburb);
		ps.setString(10, addressPostcode);
		ps.setString(11, addressCity);
		ps.setString(12, cardNo);
		ps.setString(13, CVV);
		ps.setString(14, cardHolder);

		ps.execute();

		// Once an Order is made, clear the Owner's Cart.
		clearCartByOwner(owner);
	}

	/**
	 * Updates a Customer.
	 *
	 * @param c The Customer as an Object that holds all the current details pre-update.
	 * @param fn First Name.
	 * @param ln Last Name.
	 * @param pw Password.
	 * @param email Email address.
	 * @param phone Phone Number.
	 * @param addNum Street Number.
	 * @param addStreetName Street Name.
	 * @param addSuburb Suburb.
	 * @param addPostcode Postcode.
	 * @param addCity City.
	 * @param cardNo Card Number.
	 * @param cvv CVV Security Code.
	 * @param cardHolder Card Holder.
	 * @throws SQLException
	 */
	public void update(Customer c, String fn, String ln, String pw, String email, String phone, String addNum, String addStreetName, String addSuburb, String addPostcode, String addCity, String cardNo, String cvv, String cardHolder) throws SQLException
	{
		String instruction = "UPDATE IOTBAY.CUSTOMERS SET FIRSTNAME=?, LASTNAME=?, PASSWORD=?, EMAIL=?, PHONENUMBER=?, STREETNUMBER=?, STREETNAME=?, SUBURB=?, POSTCODE=?, CITY=?, CARDNUMBER=?, CVV=?, CARDHOLDER=? WHERE EMAIL=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, fn);
		ps.setString(2, ln);
		ps.setString(3, pw);
		ps.setString(4, email.toLowerCase());
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

	/**
	 * Updates a member of Staff.
	 *
	 * @param s The Staff as an Object that holds all the current details pre-update.
	 * @param fn First Name.
	 * @param ln Last Name.
	 * @param pw Password.
	 * @param email Email address.
	 * @throws SQLException
	 */
	public void update(Staff s, String fn, String ln, String pw, String email) throws SQLException
	{
		String instruction = "UPDATE IOTBAY.STAFF SET FIRSTNAME=?, LASTNAME=?, PASSWORD=?, EMAIL=? WHERE EMAIL=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, fn);
		ps.setString(2, ln);
		ps.setString(3, pw);
		ps.setString(4, email.toLowerCase());
		ps.setString(5, s.getEmail());

		ps.executeUpdate();
	}

	/**
	 * Updates a Product.
	 *
	 * @param id The Primary Key of this Product.
	 * @param name Product Name.
	 * @param desc Product Description.
	 * @param price Price of Product.
	 * @param stock The stock of this Product.
	 * @throws SQLException
	 */
	public void updateProduct(int id, String name, String desc, String price, int stock) throws SQLException
	{
		String instruction = "UPDATE IOTBAY.PRODUCTS SET PRODUCTNAME=?, DESCRIPTION=?, PRICE=?, STOCK=? WHERE PRODUCTID=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, name);
		ps.setString(2, desc);
		ps.setFloat(3, Float.parseFloat(price));
		ps.setInt(4, stock);
		ps.setInt(5, id);

		ps.executeUpdate();
	}

	/**
	 * Updates the Cart's desired quantity of a Product.
	 *
	 * @param pid The Product to update the quantity of in the Cart.
	 * @param owner The Email address who owns the Cart.
	 * @param newQuantity The desired number of Product pid.
	 * @throws SQLException
	 */
	public void updateCart(int pid, String owner, int newQuantity) throws SQLException
	{
		String instruction = "UPDATE IOTBAY.ORDERLINEITEM SET QUANTITY=? WHERE PRODUCTID=? AND OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setInt(1, newQuantity);
		ps.setInt(2, pid);
		ps.setString(3, owner);

		ps.executeUpdate();
	}

	/**
	 * Removes a record from database, given an email.
	 *
	 * @param database The Database to modify.
	 * @param email The Email address to remove, based on existence within database.
	 * @throws SQLException
	 */
	public void remove(String database, String email) throws SQLException
	{
		String instruction = "DELETE FROM IOTBAY." + database + " WHERE EMAIL=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, email);

		ps.executeUpdate();
	}

	/**
	 * Removes a Product from a Cart.
	 *
	 * @param pid The Primary Key of the Product to remove.
	 * @param owner The Email address that owns this Cart.
	 * @throws SQLException
	 */
	public void removeFromCart(int pid, String owner) throws SQLException
	{
		String instruction = "DELETE FROM IOTBAY.ORDERLINEITEM WHERE PRODUCTID=? AND OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setInt(1, pid);
		ps.setString(2, owner);

		ps.executeUpdate();
	}

	/**
	 * Clears the owner's Cart.
	 *
	 * @param owner The Email address who owns this Cart.
	 * @throws SQLException
	 */
	public void clearCartByOwner(String owner) throws SQLException
	{
		String instruction = "DELETE FROM IOTBAY.ORDERLINEITEM WHERE OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, owner);

		ps.executeUpdate();
	}

	/**
	 * Cancels a Confirmed Order.
	 *
	 * @param oid The Primary Key of the Order to remove.
	 * @param owningEmail The Email address of the Customer who made the Order.
	 * @throws SQLException
	 */
	public void cancelOrder(int oid, String owningEmail) throws SQLException
	{
		String getInstruction = "SELECT * FROM IOTBAY.ORDERS WHERE ORDERID=?";

		PreparedStatement get_ps = connection.prepareStatement(getInstruction);
		get_ps.setInt(1, oid);

		ResultSet r = get_ps.executeQuery();
		while (r.next())
		{
			// The Product IDs and their corresponding Quantities.
			String pids = r.getString(5);
			String quas = r.getString(6);

			// Translate from our Array Hack to a String[].
			String[] buffer_pids = pids.split(":", 1 << 30);
			String[] buffer_quas = quas.split(":", 1 << 30);

			// If the arrays aren't equal in length, something has gone very wrong.
			if (buffer_pids.length != buffer_quas.length)
			{
				throw new IllegalArgumentException("DBManager::cancelOrder() -> buffer_pid != buffer_quas. buffer_pid = " + buffer_pids.length + " buffer_quas = " + buffer_quas.length);
			}

			// The number of Products.
			int count = buffer_pids.length;
			--count; // Ignore the last ':'.

			// Parse our Array Hack back into their original; as integers.
			int[] productIDs = new int[count];
			int[] quantities = new int[count];

			for (int i = 0; i < count; ++i)
			{
				productIDs[i] = Integer.parseInt(buffer_pids[i]);
				quantities[i] = Integer.parseInt(buffer_quas[i]);
			}

			// Restore the Product's Stock/Quantities using the data from the Array Hack.
			for (int i = 0; i < count; ++i)
			{
				Product p = findProduct(productIDs[i]);
				updateProduct(productIDs[i], p.getName(), p.getDescription(), p.getPrice() + "", p.getQuantity() + quantities[i]);
			}

			// Finally, mark this Order as Cancelled.
			String instruction = "UPDATE IOTBAY.ORDERS SET STATUS=? WHERE ORDERID=?";

			PreparedStatement ps = connection.prepareStatement(instruction);
			ps.setString(1, "Cancelled");
			ps.setInt(2, oid);

			ps.executeUpdate();
		}
	}

	public final ArrayList<Customer> injectCustomers() throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.CUSTOMERS";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Customer> dbInjected = new ArrayList();

		while (r.next())
		{

			// Inject.
			dbInjected.add(resultSetToCustomer(r));
		}

		return dbInjected;
	}

	public final ArrayList<Staff> injectStaff() throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.STAFF";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Staff> dbInjected = new ArrayList();

		while (r.next())
		{

			// Inject.
			dbInjected.add(resultSetToStaff(r));
		}

		return dbInjected;
	}

	public final ArrayList<Product> injectProducts() throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.PRODUCTS";
		ResultSet r = statement.executeQuery(instruction);
		ArrayList<Product> dbInjected = new ArrayList();

		while (r.next())
		{

			// Inject.
			dbInjected.add(resultSetToProduct(r));
		}

		return dbInjected;
	}

	public final ArrayList<OrderLineItem> injectOLI(String email) throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.ORDERLINEITEM WHERE OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, email.toLowerCase());

		ResultSet r = ps.executeQuery();
		ArrayList<OrderLineItem> dbInjected = new ArrayList();

		while (r.next())
		{

			// Inject.
			dbInjected.add(resultSetToOLI(r, email));
		}

		cart = dbInjected;

		return dbInjected;
	}

	public final ArrayList<OrderLineItem> injectOLIAnonymous() throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.ORDERLINEITEM WHERE OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, AnonymousUserEmail);

		ResultSet r = ps.executeQuery();

		ArrayList<OrderLineItem> dbInjected = new ArrayList();

		while (r.next())
		{

			// Inject.
			dbInjected.add(resultSetToOLI(r, AnonymousUserEmail));
		}

		cart = dbInjected;

		return cart;
	}

	public ArrayList<Order> injectOrders(String owningEmail) throws SQLException
	{
		String instruction = "SELECT * FROM IOTBAY.ORDERS WHERE OWNER=?";

		PreparedStatement ps = connection.prepareStatement(instruction);
		ps.setString(1, owningEmail);

		ResultSet r = ps.executeQuery();

		ArrayList<Order> dbInjected = new ArrayList();

		Customer owner = findCustomer(owningEmail);

		while (r.next())
		{
			dbInjected.add(resultSetToOrder(r, owner));
		}

		orders = dbInjected;

		return dbInjected;
	}

	private Customer resultSetToCustomer(ResultSet r) throws SQLException
	{

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

	private Staff resultSetToStaff(ResultSet r) throws SQLException
	{

		// Personal Information.
		String em = r.getString(1);
		String fn = r.getString(2);
		String ln = r.getString(3);
		String pw = r.getString(4);

		return new Staff(fn, ln, pw, em);
	}

	private Product resultSetToProduct(ResultSet r) throws SQLException
	{

		// Product Information.
		String pname = r.getString(2);
		String descr = r.getString(3);
		int quantity = r.getInt(4);
		float fPrice = r.getFloat(5);

		return new Product(pname, descr, fPrice, quantity);
	}

	private OrderLineItem resultSetToOLI(ResultSet r, String ownerEmail) throws SQLException
	{

		// Order Line Information.
		int pid = r.getInt(2);
		int qua = r.getInt(3);

		return new OrderLineItem(findProduct(pid), findCustomer(ownerEmail), qua);
	}

	private Order resultSetToOrder(ResultSet r, Customer owner) throws SQLException
	{
		// Order Information.
		int ID = r.getInt(1);
		float price = r.getFloat(3);
		String status = r.getString(4);
		String pids = r.getString(5);
		String quas = r.getString(6);
		String purchaseDate = r.getString(7);

		// Shipping Address.
		String addNum = r.getString(8);
		String addNam = r.getString(9);
		String sub = r.getString(10);
		String pc = r.getString(11);
		String city = r.getString(12);

		// Payment Information.
		String cNo = r.getString(13);
		String cvv = r.getString(14);
		String cHo = r.getString(15);

		// Construct.
		Address a = new Address(addNum, addNam, sub, pc, city);
		PaymentInformation p = new PaymentInformation(cNo, cvv, cHo);

		// Format to match pids with quantities:
		// 1:3:4:15:           // Product IDs
		// 21:45:24:5:         // Quantities.
		String[] buffer_pids = pids.split(":", 1 << 30);
		String[] buffer_quas = quas.split(":", 1 << 30);

		// The number of Products.
		int count = buffer_pids.length;

		// If the arrays aren't equal in length, something has gone very wrong.
		if (buffer_pids.length != buffer_quas.length)
		{
			throw new IllegalArgumentException("DBManager::resultSetToOrder() -> buffer_pid != buffer_quas. buffer_pid = " + buffer_pids.length + " buffer_quas = " + buffer_quas.length);
		}

		// Ignore the last ':'.
		--count;

		// Parse our Array Hack back into their original; as integers.
		int[] productIDs = new int[count];
		int[] quantities = new int[count];

		for (int i = 0; i < count; ++i)
		{
			productIDs[i] = Integer.parseInt(buffer_pids[i]);
			quantities[i] = Integer.parseInt(buffer_quas[i]);
		}

		// Construct.
		ArrayList<OrderLineItem> OLIs = new ArrayList();

		for (int i = 0; i < count; ++i)
		{
			OLIs.add(new OrderLineItem(findProduct(productIDs[i]), owner, quantities[i]));
		}

		return new Order(ID, owner, OLIs, price, status, purchaseDate, a, p);
	}
}
