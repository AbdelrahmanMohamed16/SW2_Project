package Login_RegisterManegment;

import User.*;

public interface DataBase {

    public void AddAccount(instaPayAccount acc);
    public instaPayAccount getUserInstaAccount (String username , String password);
    public externalAccount getUserExtrnalAccount (String username);


    public externalAccount getUserExtrnalAccount (externalAccount acc);

}
