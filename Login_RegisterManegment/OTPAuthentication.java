package Login_RegisterManegment;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class OTPAuthentication implements Authentication {
    private  String OTP;

    public String getOTP() {
        return OTP;
    }

    @Override
    public String Authenticate(String Number) {
        Random rand = new Random();
        int randomNo = rand.nextInt(900000) + 100000; // range: 100000 - 999999
        this.OTP =  String.valueOf(randomNo);
        return OTP;
    }


}