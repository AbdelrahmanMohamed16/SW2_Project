package Login_RegisterManegment;

import User.*;

public interface loginService {

	DataBase db = MemoryDB.getInstance();
	/**
	 * 
	 * @param Name
	 * @param Password
	 */
	abstract instaPayAccount login(String Name, String Password);

}