package Login_RegisterManegment;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class OTPAuthentication implements Authentication {
    private static String OTP;


    public OTPAuthentication(){
        // Generate a random 6-digit OTP
        Random rand = new Random();
        int randomNo = rand.nextInt(900000) + 100000; // range: 100000 - 999999
        OTPAuthentication.OTP =  String.valueOf(randomNo);
    }



    public String getOTP() {
        return OTP;
    }

    @Override
    public String Authenticate(String Number) {

        try {
            // Construct data
            String apiKey = "apikey=" + "NjM0NjYxMzAzNzZmNDE2ZjQ5NDIzNTc4Njc2ZTY2Nzc=";
            String message = "&message=" + "Greetings from Simplifying Tech! Have a nice day!";
            //String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "01115429561";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message ;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            System.out.println(stringBuffer.toString());
            rd.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return OTP;
    }



}