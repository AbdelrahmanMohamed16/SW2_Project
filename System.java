import Login_RegisterManegment.*;
import PaymentManagement.*;
import User.*;

import java.util.Scanner;


public class System {

	private instaPayAccount currentUser;
	private registrationFactory registerFactory;
	public Authentication authentication;


	public void Login() {
		// TODO - implement System.Login
		throw new UnsupportedOperationException();
	}

	public instaPayAccount Signup(String type) {
		registration register = registerFactory.createRegistration(type);
		Scanner in = new Scanner(java.lang.System.in);
		if (register != null) {
			//--------> input of user based on return type of register , then save this info in appropriate object
			java.lang.System.out.print("Enter OTP: ");
			String otp = in.next();
			if(checkOTP(otp)){
				//return register.register(acc);     //please replace acc with the object that you save info in it above
			}
		}
		return null;
	}

	public boolean checkOTP(String otp)
	{
		 return authentication.Authenticate(currentUser.getAccount().getAccountNumber()).equals(otp);
	}

	public void Transfer() {
		// TODO - implement System.Transfer
		throw new UnsupportedOperationException();
	}

	public void getAccount() {
		// TODO - implement System.getAccount
		throw new UnsupportedOperationException();
	}

}