package PaymentManagement;

public interface paymentAPI {



	/**
	 * 
	 * @param accountNumber
	 */
	abstract double getBalance(String accountNumber);

	/**
	 * 
	 * @param bill
	 */
	abstract void payBill(Bill bill,String accountNumber);

}