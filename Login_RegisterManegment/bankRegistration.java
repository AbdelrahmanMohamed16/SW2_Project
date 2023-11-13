package Login_RegisterManegment;

import User.instaPayAccount;

public class bankRegistration extends registration {
    @Override
    public boolean isValidData(instaPayAccount acc) {
        return checkUsername(acc.getUserName()) && checkPassword(acc.getPassword());
    }
}