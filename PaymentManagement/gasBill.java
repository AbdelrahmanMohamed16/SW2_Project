package PaymentManagement;

public class gasBill extends Bill {

	private String gasCompany;
	private String gasConsumed;

	public gasBill(String gasCompany, String gasConsumed, String billNumber, double Amount) {
		super(billNumber,Amount);
		this.gasCompany = gasCompany;
		this.gasConsumed = gasConsumed;
	}
}