import Login_RegisterManegment.*;
import PaymentManagement.*;
import User.*;

import java.util.Scanner;


public class system {

	private static instaPayAccount currentUser;
	private static registrationFactory registerFactory;
	public static paymentAPI API ;
	public static Authentication authentication = new OTPAuthentication();
	public static RegistrationValidation validator ;

	public static DataBase DB;


	public void Login() {
		// TODO - implement System.Login
		throw new UnsupportedOperationException();
	}

	static public boolean Signup(instaPayAccount ac,externalAccount ex,String type) {
		registration register = registerFactory.createRegistration(type);
		Scanner in = new Scanner(System.in);
		if (register != null) {
			//--------> input of user based on return type of register , then save this info in appropriate object
			System.out.print("Enter OTP: ");
			String otp = in.next();
			if(checkOTP(otp)){
				register.register(ac,ex);
				return true;//please replace acc with the object that you save info in it above
			}
		}
		return false;
	}

	public static boolean checkOTP(String otp) {
		 return authentication.Authenticate(currentUser.getAccount().getAccountNumber()).equals(otp) || true;
	}

	public void Transfer() {
		// TODO - implement System.Transfer
		throw new UnsupportedOperationException();
	}

	public void getAccount() {
		// TODO - implement System.getAccount
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		externalAccount  extAccount = null;
		String BankNumber ="" ;
		String walletType ="";
		// Welcome, Message
		System.out.println("Welcome to InstPay OnlinePayment Platform");


		// Ask User to Login or to Register


		// if Login
		do {
			System.out.println("1-Login\n2-Register\n>");
			String opStart = scanner.nextLine();
			if(opStart.matches("(?i)(Login|1)")  ){
				System.out.println("Enter UserName: ");
				String username = scanner.nextLine();

				System.out.println("Enter Password: ");
				String password = scanner.nextLine();

				loginService login = new defaultSignin();
				currentUser =  login.login(username,password);
				if(currentUser == null){
					System.out.println("User not Found");
				}
				else{
					break;
				}
			}
			else if(opStart.matches("(?i)(Register|2)")){
				// take username / password

				System.out.println("Enter UserName: ");
				String username = scanner.nextLine();

				System.out.println("Enter Password: ");
				String password = scanner.nextLine();

				validator = new Validator();
				currentUser = new instaPayAccount(username,password);

				if(!validator.checkUser(currentUser)) {
					System.out.println("InValid User Data");
					continue;
				}
				// choose type of ex-Account
				System.out.println("Enter Account type(Bank/Wallet): ");
				String accountType = scanner.nextLine();
				if(accountType.equalsIgnoreCase("wallet")){
					System.out.println("Enter Wallet type(VFCash/Bank/Fawry): ");
					 walletType = scanner.nextLine();
					if(walletType.equalsIgnoreCase("VFcash")){
						API = new VFCashAPI();
					}
					else if(walletType.equalsIgnoreCase("Bank")){
						API = new bankWalletAPI();
					}
					else if(walletType.equalsIgnoreCase("Fawry")){
						API = new paymentCompaniesAPI();
					}
					else
					{
						System.out.println("Wrong wallet Type");
						continue;
					}

				}
				else if(accountType.equalsIgnoreCase("bank")){
					API = new bankAPI();
					System.out.println("Enter Bank Account Number: ");
					 BankNumber = scanner.nextLine();
				}
				else {
					System.out.println("not wallet nor bank");
					continue;
				}
				System.out.println("Enter Mobile Number: ");
				String accountNumber = scanner.nextLine();

				if(accountType.equalsIgnoreCase("bank")){
					extAccount = new bankAccount(accountNumber,BankNumber);
				}
				else if(accountType.equalsIgnoreCase("wallet")){
					extAccount = new walletAccount(accountNumber,walletType);
				}

				// OTP
				if(!Signup(currentUser,extAccount,accountType)){
					System.out.println("Error happen..Try Again");
					continue;
				}


				// if BAnk take Mobile number / Bank Account
				// validate account / OTP Mobile
				// if Wallet take Mobile
				// validate mobile / OTP mobile

				break;
			}
			System.out.println("InValid Input");

		}while (true);

		// Open to transfer or to LogOut
	}
}