package User;

public class bankAccount extends externalAccount {
	public String bankNumber;

	public bankAccount(String accountNumber , String bankNumber ){
		this.accountNumber = accountNumber;
		this.bankNumber = bankNumber ;
	}

	public Boolean compare(externalAccount ex){

		if(((bankAccount)ex).getData() == this.bankNumber){
			return true ;
		}
		return false;
	}

	@Override
	public void displayMenu() {
		System.out.println("Transfer Menu:\n" +
				"1- Transfer to InstaPay Account\n" +
				"2- Pay Bill\n" +
				"3- Transfer to Wallet\n" +
				"4- Get Balance\n" +
				"5- Transfer to Bank Account\n");
	}
	public  String getData(){
		return this.bankNumber;
	}
}