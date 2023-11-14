package Login_RegisterManegment;

import User.instaPayAccount;

public class Validator implements RegistrationValidation{
    public boolean isValidData(instaPayAccount acc) {
        return checkUsername(acc.getUserName()) && checkPassword(acc.getPassword());
    }
    public  boolean checkUser(instaPayAccount acc) {
        // check UserName and pass for instaPay data
        if(isValidData(acc)){
            return true;
        }
        return false;
    }

    public  boolean checkUsername(String username){
        return username.length() >= 6 ;
    }

    public boolean checkPassword (String password){
        return password.length() >= 8;
    }


}
