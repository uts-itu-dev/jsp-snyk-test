package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.User;

/**
 * The invoice generated after an Order.
 *
 * @author Michael Wu
 */
public class Invoice implements Serializable {

	private final User owner;
	private final Order order;

	public Invoice(User u, Order o) {
		owner = u;
		order = o;
	}

	public User getOwner() {
		return owner;
	}

	public Order getOrder() {
		return order;
	}

}
