package Login_RegisterManegment;

import User.*;

import java.util.Scanner;

public abstract class registration {
	public DataBase db = new DataBase();
	public abstract boolean isValidData(instaPayAccount acc);

	public instaPayAccount register(instaPayAccount acc) {
		if(isValidData(acc)){
			db.AddAccount(acc);
			return acc;
		}
		return null;
	}

	public boolean checkUsername(String username){
		return db.accounts.contains(username);
	}

	public boolean checkPassword (String password){
		return password.length() >= 8;
	}

}