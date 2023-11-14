package Login_RegisterManegment;

import User.instaPayAccount;

public interface RegistrationValidation {
    public boolean isValidData(instaPayAccount acc);
    public  boolean checkUser(instaPayAccount acc) ;
    public boolean checkPassword (String password);
}
