package PaymentManagement;

public class gasBill extends Bill {

	private String gasCompany;
	private String gasConsumed;

	public gasBill( String billNumber, double Amount, String gasCompany, String gasConsumed) {
		super(billNumber,Amount);
		this.gasCompany = gasCompany;
		this.gasConsumed = gasConsumed;
	}
}