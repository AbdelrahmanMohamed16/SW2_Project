package PaymentManagement;

public interface paymentAPI {

	/**
	 * 
	 * @param scAccNumber
	 * @param desAccNumber
	 */
	abstract void Transfer(String scAccNumber, String desAccNumber);

	/**
	 * 
	 * @param accountNumber
	 */
	abstract double getBalance(String accountNumber);

	/**
	 * 
	 * @param bill
	 */
	abstract void payBill(Bill bill);

}