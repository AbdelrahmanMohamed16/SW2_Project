package PaymentManagement;

public class waterBill extends Bill {

	private String waterCompany;
	private String waterConsumed;

	public waterBill(String billNumber, double Amount, String waterCompany, String waterConsumed) {
		super(billNumber, Amount);
		this.waterCompany = waterCompany;
		this.waterConsumed = waterConsumed;
	}
}