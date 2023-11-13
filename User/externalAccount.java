package User;

import PaymentManagement.paymentAPI;

public abstract class externalAccount {

	private String accountNumber;
	private paymentAPI transferAPI;

	public double getBalance() {
		// TODO - implement externalAccount.getBalance
		throw new UnsupportedOperationException();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public abstract void displayMenu();

}