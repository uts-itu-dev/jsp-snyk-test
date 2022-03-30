package Model.IoTBay;

import Model.IoTBay.Person.Address;

import java.io.Serializable;

/**
 * Shipment details for an Order from IoTBay.
 *
 * @author Michael Wu
 */
public class Shipment implements Serializable {

	private Address destination;
	private String status;
	private String options;

	public Shipment(Address destination, String status, String options) {
		this.destination = destination;
		this.status = status;
		this.options = options;
	}

	public Address getDestination() {
		return destination;
	}

	public String getStatus() {
		return status;
	}

	public String getOptions() {
		return options;
	}

	public void setDestination(Address destination) {
		this.destination = destination;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}
