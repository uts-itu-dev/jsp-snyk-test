package Model.IoTBay.Person;

import Model.IoTBay.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * A Registered Customer of IoTBay.
 *
 * @author Michael Wu
 */
public class Customer extends User implements Serializable {

	private String phoneNumber;
	private Date DOB;
	private ArrayList<Order> purchaseHistory;
	private PaymentInformation payment;
	private boolean bIsRegistered;
	
	public Customer(String fn, String ln, String pw, String em){
		super(fn, ln, pw, em);
	}

	public Customer(String fn, String ln, String pw, String em, Address add, String pn) {
		super(fn, ln, pw, em, add);

		phoneNumber = pn;
	}

	public Customer(String fn, String ln, String pw, String em, Address add, String pn, PaymentInformation pi) {
		this(fn, ln, pw, em, add, pn);
		
		payment = pi;
	}
	
	@Override
	public void addToDatabase() {
		
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public Date getDOB() {
		return DOB;
	}
	
	public ArrayList<Order> getPurchaseHistory() {
		return purchaseHistory;
	}
	
	public PaymentInformation getPayment() {
		return payment;
	}
	
	public boolean getRegistration() {
		return bIsRegistered;
	}

	public void setPhoneNumber(String v) {
		phoneNumber = v;
	}
	
	public void setPayment(PaymentInformation pi) {
		payment = pi;
	}
	
	public void setPayment(String cn, String cvv, String ch) {
		setPayment(new PaymentInformation(cn, cvv, ch));
	}
	
	public void setRegistered() {
		bIsRegistered = true;
	}
	
	public void addOrder(Order o) {
		purchaseHistory.add(o);
	}
}
