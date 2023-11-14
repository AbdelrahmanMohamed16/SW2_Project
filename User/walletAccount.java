package User;

public class walletAccount extends externalAccount {

	public String walletType;
	public walletAccount(String accountNumber , String walletType){
			this.accountNumber = accountNumber;
			this.walletType = walletType;
	}
	public Boolean compare(externalAccount ex){

		if(ex.accountNumber == this.accountNumber){
			return true ;
		}
		return false;
	}

	@Override
	public void displayMenu() {

	}
	public  String getData(){
		return this.walletType;
	}


}