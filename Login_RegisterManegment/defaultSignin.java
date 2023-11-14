package Login_RegisterManegment;

import User.instaPayAccount;

public class defaultSignin implements loginService{

    public instaPayAccount login(String Name, String Password){
        return db.getUserInstaAccount(Name , Password);
    }
}