package Login_RegisterManegment;

import User.instaPayAccount;

import java.util.ArrayList;

public class MemoryDB implements DataBase{


    public ArrayList<instaPayAccount> accounts = new ArrayList<>();

    public void AddAccount(instaPayAccount acc)
    {
        accounts.add(acc);
    }


    public instaPayAccount getUserInstaAccount (String username , String password) {
        for (instaPayAccount account : accounts) {
            if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }
//    String getExternalAccount(instaPayAccount acc)
//    {
//
//    }
}
