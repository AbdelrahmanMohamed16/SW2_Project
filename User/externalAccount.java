package User;

import PaymentManagement.paymentAPI;

public abstract class externalAccount {

	public String accountNumber;
	public paymentAPI transferAPI;

	public double getBalance() {
		// TODO - implement externalAccount.getBalance
		throw new UnsupportedOperationException();
	}

	public abstract Boolean compare(externalAccount ex);
	public String getAccountNumber() {
		return accountNumber;
	}

	public abstract void displayMenu();
	public abstract String getData();

}