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

	public Product(String name, String desc, float price) {
		productID = numberOfProducts++;
		
		this.name = name;
		description = desc;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return (double)price;
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
}
