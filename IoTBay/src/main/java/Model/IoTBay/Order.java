package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.Customer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * An Order made by a Customer : User in IoTBay.
 *
 * @author Michael Wu
 */
public class Order implements Serializable {

	private final Customer owner;
	private final ArrayList<OrderLineItem> products;
	private String orderStatus;
	private final String purchaseDate;

	public Order(Customer o, ArrayList<OrderLineItem> products, int quant) {
		owner = o;
		this.products = products;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		purchaseDate = dtf.format(now);

		setOrderStatus("Confirmed");
	}

	public final Customer getOwner() {
		return owner;
	}

	public final ArrayList<OrderLineItem> getProducts() {
		return products;
	}

	public final float getTotalCost() {
		float c = 0;

		for (OrderLineItem o : products) {
			c += o.getTotalCost();
		}

		return c;
	}

	public final String getOrderStatus() {
		return orderStatus;
	}

	public final String getPurchaseDate() {
		return purchaseDate;
	}

	public final void setOrderStatus(String v) {
		orderStatus = v;
	}
}
