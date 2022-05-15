package Model.IoTBay;

import Model.IoTBay.Person.Customer;
import java.io.Serializable;

/**
 * A single entry in a User's shopping cart.
 *
 * One entry contains a single Product, but it can be purchased in large
 * quantities.
 *
 * @author Michael Wu
 */
public class OrderLineItem implements Serializable
{

	private final Product product;
	private final Customer owner;
	private int quantity;
	private float totalCost;

	public OrderLineItem(Product product, Customer owner, int quantity)
	{
		this.product = product;
		this.owner = owner;
		this.quantity = quantity;
		
		if (product == null)
			System.out.println("P is null");

		totalCost = (float) product.getPrice() * quantity;
	}

	public Product getProduct()
	{
		return product;
	}

	public Customer getOwner()
	{
		return owner;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public float getTotalCost()
	{
		return totalCost;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
		setTotalCost();
	}

	private void setTotalCost()
	{
		totalCost = product.getPrice() * quantity;
	}
}
