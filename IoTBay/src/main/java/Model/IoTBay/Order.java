package Model.IoTBay;

import Model.IoTBay.Person.Address;
import java.io.Serializable;

import Model.IoTBay.Person.Customer;
import Model.IoTBay.Person.PaymentInformation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * An Order made by a Customer : User in IoTBay.
 *
 * @author Michael Wu
 */
public class Order implements Serializable
{

	private final int ID;
	private final Customer owner;
	private final ArrayList<OrderLineItem> products;
	private String status;
	private final String purchaseDate;
	private final Address address;
	private final PaymentInformation paymentInformation;
	private final float totalCost;

	public Order(int ID, Customer o, ArrayList<OrderLineItem> products, float price, String status, String purchaseDate, Address a, PaymentInformation pi)
	{
		this.ID = ID;

		owner = o;
		this.products = products;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		purchaseDate = dtf.format(now);

		totalCost = price;
		setOrderStatus(status);
		this.purchaseDate = purchaseDate;

		address = a;
		paymentInformation = pi;
	}

	public final int getID()
	{
		return ID;
	}

	public final Customer getOwner()
	{
		return owner;
	}

	public final ArrayList<OrderLineItem> getProducts()
	{
		return products;
	}

	public final float getTotalCost()
	{
		return totalCost;
	}

	public final String getStatus()
	{
		return status;
	}

	public final String getPurchaseDate()
	{
		return purchaseDate;
	}

	public final Address getAddress()
	{
		return address;
	}

	public final PaymentInformation getPaymentInformation()
	{
		return paymentInformation;
	}

	public final void setOrderStatus(String v)
	{
		status = v;
	}
}
