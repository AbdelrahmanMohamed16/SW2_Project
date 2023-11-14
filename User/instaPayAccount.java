package User;

public class instaPayAccount {

	public instaPayAccount(String userName , String password){
		this.userName = userName;
		this.password = password;
	}
	private externalAccount Account;
	private String userName;
	private String password;

	public externalAccount getAccount() {
		return Account;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}