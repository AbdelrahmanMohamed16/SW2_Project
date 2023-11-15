package User;

import java.util.Objects;

public class bankAccount extends externalAccount {
	public String bankNumber;

	public bankAccount(String accountNumber , String bankNumber ){
		this.mobileNumber = accountNumber;
		this.bankNumber = bankNumber ;
	}

	public Boolean compare(externalAccount ex){
		return Objects.equals(((bankAccount)ex).getData(), this.bankNumber);
	}

	@Override
	public void displayMenu() {
		System.out.println("Transfer Menu:\n" +
				"1- Transfer to InstaPay Account\n" +
				"2- Pay Bill\n" +
				"3- Transfer to Wallet\n" +
				"4- Get Balance\n" +
				"5- Transfer to Bank Account\n" +
				" press any button else to SignOut");
	}
	public  String getData(){
		return bankNumber;
	}
}