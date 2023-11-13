package Login_RegisterManegment;

import User.*;

public interface loginService {

	/**
	 * 
	 * @param Name
	 * @param Password
	 */
	abstract instaPayAccount login(String Name, String Password);

}