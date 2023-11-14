package Login_RegisterManegment;

import User.*;

public interface loginService {

	MemoryDB db = new MemoryDB();
	/**
	 * 
	 * @param Name
	 * @param Password
	 */
	abstract instaPayAccount login(String Name, String Password);

}