package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.Customer;
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
	private final Order order;
	private final Date date;

	public Invoice(Customer u, Order o) {
		invoiceID = numberOfInvoices++;
		
		order = o;
		
		date = Calendar.getInstance().getTime();
	}

	public Order getOrder() {
		return order;
	}

	public Customer getOwner() {
		return getOrder().getOwner();
	}

	public Date getDate() {
		return date;
	}
}
