package Login_RegisterManegment;

import User.instaPayAccount;

public class walletRegistration extends registration {
    @Override
    public boolean isValidData(instaPayAccount acc) {
        return checkUsername(acc.getUserName()) && checkPassword(acc.getPassword());
    }
}