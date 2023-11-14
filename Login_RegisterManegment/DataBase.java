package Login_RegisterManegment;

import User.instaPayAccount;

public interface DataBase {
    public void AddAccount(instaPayAccount acc);
    public instaPayAccount getUserInstaAccount (String username , String password);
}
