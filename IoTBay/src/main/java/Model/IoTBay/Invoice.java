package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.User;
import java.util.Calendar;
import java.util.Date;

/**
 * The invoice generated after an Order.
 *
 * @author Michael Wu
 */
public class Invoice implements Serializable {
	private static int numberOfInvoices = 0;

	public final int invoiceID;
	private final User owner;
	private final Order order;
	private final Date date;

	public Invoice(User u, Order o) {
		invoiceID = numberOfInvoices++;
		
		owner = u;
		order = o;
		
		date = Calendar.getInstance().getTime();
	}

	public User getOwner() {
		return owner;
	}

	public Order getOrder() {
		return order;
	}

	public Date getDate() {
		return date;
	}
}
