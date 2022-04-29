package Model.IoTBay.Person;

import java.io.Serializable;
import java.util.Date;

/**
 * Card details.
 *
 * @author Michael Wu
 */
public class PaymentInformation implements Serializable {

	private String cardNo;
	private String cvv;
	private String cardHolder;
	private Date expiryDate;
	
	public PaymentInformation(String cardNo, String cvv, String cardHolder) {
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.cardHolder = cardHolder;
	}

	public PaymentInformation(){
		this("", "", "");
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getCVV() {
		return cvv;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setCVV(String cvv) {
		this.cvv = cvv;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
