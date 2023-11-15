package User;

import PaymentManagement.paymentAPI;

public abstract class externalAccount {

	public String mobileNumber;
	public paymentAPI transferAPI;

	public double getBalance() {
		// TODO - implement externalAccount.getBalance
		throw new UnsupportedOperationException();
	}

	public abstract Boolean compare(externalAccount ex);
	public String getMobileNumber() {
		return mobileNumber;
	}

	public abstract void displayMenu();

}