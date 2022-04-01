package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.Customer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * An Order made by a Customer : User in IoTBay.
 *
 * @author Michael Wu
 */
public class Order implements Serializable {
	private static int numberOfOrders = 0;

	public final int orderID;
	private final Customer owner;
	private ArrayList<OrderLineItem> products;
	private String orderStatus;
	private Date purchaseDate;

	public Order(Customer o, ArrayList<OrderLineItem> products, int quant) {
		orderID = numberOfOrders++;
		
		owner = o;
		this.products = products;
		
		purchaseDate = Calendar.getInstance().getTime();
	}

	public Customer getOwner() {
		return owner;
	}
	
	public ArrayList<OrderLineItem> getProducts() {
		return products;
	}

	public float getTotalCost() {
		float c = 0;
		
		for (OrderLineItem o : products)
			c += o.getTotalCost();
		
		return c;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setOrderStatus(String v) {
		orderStatus = v;
	}
}
