package Login_RegisterManegment;

public class registrationFactory {

	public registration createRegistration(String type) {
		if(type == "Bank")
		{
			return new bankRegistration();
		}
		else if(type == "Wallet")
		{
			return new walletRegistration();
		}
		return null;
	}

}