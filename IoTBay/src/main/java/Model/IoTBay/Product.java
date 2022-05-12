package Model.IoTBay;

import java.io.Serializable;

/**
 * A device sold on IoTBay.
 *
 * @author Michael Wu
 */
public class Product implements Serializable {
	private static int numberOfProducts = 0;

	public final int productID;
	private String name;
	private String description;
	private float price;
	private int quanti;

	public Product(String name, String desc, float price, int quanti) {
		productID = numberOfProducts++;
		
		this.name = name;
		description = desc;
		this.price = price;
		this.quanti = quanti;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quanti;
	}

	public void setName(String v) {
		name = v;
	}

	public void setDescription(String v) {
		description = v;
	}

	public void setPrice(float f) {
		price = f;
	}
	
	public void setQuantity(int q) {
		quanti = q;
	}
}
