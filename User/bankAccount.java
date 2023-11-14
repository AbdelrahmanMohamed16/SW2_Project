package User;

public class bankAccount extends externalAccount {
	public String bankNumber;

	public bankAccount(String accountNumber , String bankNumber ){
		this.accountNumber = accountNumber;
		this.bankNumber = bankNumber ;
	}

	public Boolean compare(externalAccount ex){

		if(this.getData() == this.bankNumber){
			return true ;
		}
		return false;
	}

	@Override
	public void displayMenu() {

	}
	public  String getData(){
		return this.bankNumber;
	}
}