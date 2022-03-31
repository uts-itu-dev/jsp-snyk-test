package Model.IoTBay;

import Model.IoTBay.Person.User;
import java.io.Serializable;

/**
 * A single entry in a User's shopping cart.
 * 
 * One entry contains a single Product, but it can be purchased in large quantities.
 * 
 * @author Michael Wu
 */
public class OrderLineItem implements Serializable {
	private Product product;
	private User user;
	private int quantity;
	private float totalCost;

	public OrderLineItem(Product product, User user, int quantity) {
		this.product = product;
		this.user = user;
		this.quantity = quantity;
		
		totalCost = product.getPrice() * quantity;
	}

	public Product getProductID() {
		return product;
	}

	public User getUserID() {
		return user;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
