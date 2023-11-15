package PaymentManagement;

public abstract class Bill {

	private String billNumber;
	private double Amount ;
	public  double getBillAmount()
	{
		return Amount;
	}
	Bill(String billNumber, double Amount){
		this.billNumber = billNumber;
		this.Amount = Amount;
	}

}