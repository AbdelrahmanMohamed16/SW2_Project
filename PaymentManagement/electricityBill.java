package PaymentManagement;

public class electricityBill extends Bill {

	private String electricityCompany;
	private String electricityConsumed;

	electricityBill(String billNumber, double Amount, String electricityCompany, String electricityConsumed) {
		super(billNumber, Amount);
		this.electricityCompany = electricityCompany;
		this.electricityConsumed = electricityConsumed;
	}
}