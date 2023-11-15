package User;

import java.util.Objects;

public class walletAccount extends externalAccount {

	public WalletType walletType;
	public walletAccount(String accountNumber , WalletType walletType){
			this.accountNumber = accountNumber;
			this.walletType = walletType;
	}
	public Boolean compare(externalAccount ex){

		return Objects.equals(ex.accountNumber, this.accountNumber);
	}

	@Override
	public void displayMenu() {
		System.out.println("Transfer Menu:\n" +
				"1- Transfer to InstaPay Account\n" +
				"2- Pay Bill\n" +
				"3- Transfer to Wallet\n" +
				"4- Get Balance\n");
	}
	public  WalletType getData(){
		return this.walletType;
	}


}