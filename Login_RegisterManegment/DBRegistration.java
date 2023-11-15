package Login_RegisterManegment;

import User.externalAccount;
import User.instaPayAccount;
import Application.*;

public class DBRegistration extends registration {
    public void register(instaPayAccount acc , externalAccount ext){
        // check external exist
        acc.Account = ext;
        system.DB.AddAccount(acc);
    }

}