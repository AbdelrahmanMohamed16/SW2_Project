package Login_RegisterManegment;

import User.*;

import java.util.ArrayList;

public class MemoryDB implements DataBase{
    public static MemoryDB Instance;

    private MemoryDB(){}

    public static synchronized MemoryDB getInstance(){
        if (Instance == null){
            Instance = new MemoryDB();
        }
        return Instance;
    }
    public ArrayList<instaPayAccount> accounts = new ArrayList<>();
    // for register
    public void AddAccount(instaPayAccount acc)
    {
        accounts.add(acc);
    }

    // for login
    public instaPayAccount getUserInstaAccount (String username , String password) {
        for (instaPayAccount account : accounts) {
            if (account.getUserName().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }
    // for transfer
    public externalAccount getUserExtrnalAccount (String username) {
        for (instaPayAccount account : accounts) {
            if (account.getUserName().equals(username)) {
                return account.Account;
            }
        }
        return null;
    }
}
