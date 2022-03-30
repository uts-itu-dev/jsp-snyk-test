package Model.IoTBay;

import java.io.Serializable;

import Model.IoTBay.Person.User;

/**
 * An Order made by a Customer : User in IoTBay.
 *
 * @author Michael Wu
 */
public class Order implements Serializable {

	private final User owner;
	private final Product product;
	private final int quantity;
	private final float totalCost;
	private String orderStatus;

	public Order(User o, Product p, int quant) {
		owner = o;
		product = p;
		quantity = quant;
		totalCost = p.getPrice() * quant;
	}

	public User getOwner() {
		return owner;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String v) {
		orderStatus = v;
	}
}
