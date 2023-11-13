package User;

public abstract class externalAccount {

	private String accountNumber;
	private paymentAPI transferAPI;

	public double getBalance() {
		// TODO - implement externalAccount.getBalance
		throw new UnsupportedOperationException();
	}

	public abstract void displayMenu();

}