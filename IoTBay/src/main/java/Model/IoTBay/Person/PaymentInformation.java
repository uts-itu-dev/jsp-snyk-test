package Model.IoTBay.Person;

/**
 * Card details.
 *
 * @author Michael Wu
 */
public class PaymentInformation {

	private String cardNo;
	private String security;
	private String cardHolder;
	// Expiry Date.

	public PaymentInformation(String cardNo, String security, String cardHolder) {
		this.cardNo = cardNo;
		this.security = security;
		this.cardHolder = cardHolder;
	}

	public String getCardNo() {
		return cardNo;
	}

	public String getSecurity() {
		return security;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
}
