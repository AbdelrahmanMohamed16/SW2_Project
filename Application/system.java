package Application;

import Login_RegisterManegment.*;
import PaymentManagement.*;
import User.*;

import java.util.Scanner;


public class system {

	private static instaPayAccount currentUser;
	private static registration Registration;
	public static paymentAPI API;
	public static paymentFactory APICreator;
	public static Authentication authentication = new OTPAuthentication();
	public static RegistrationValidation validator = new Validator() ;
	public static DataBase DB = MemoryDB.getInstance();
	public static Scanner scanner = new Scanner(System.in);
	public static String accountType;



	public static boolean Login() {
		// TODO - implement System.Login
		System.out.println("Enter UserName: ");
		String username = scanner.nextLine();

		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		loginService lg = new defaultSignin();
		currentUser =  lg.login(username,password);
		if(currentUser == null){
			System.out.println("User not Found");
			return false;
		}
		else{
			if(currentUser.Account instanceof bankAccount){
				accountType = "Bank";
				APICreator = new bankAPIFactory();
				API = APICreator.createTransformAPI();
				System.out.println(currentUser.Account);
			}
			else if (currentUser.Account instanceof walletAccount){
				System.out.println(currentUser.Account);
				accountType = "Wallet";
				APICreator = new walletAPIFactoy();
				API = APICreator.createTransformAPI();
			}
			else {
				System.out.println("External Account Not Found");
				return false;
			}
			return true;
		}
	}

	static public boolean Signup(instaPayAccount ac,externalAccount ex,String type) {
		Registration = new DBRegistration();
		Scanner in = new Scanner(System.in);
		if (Registration != null) {
			//--------> input of user based on return type of register , then save this info in appropriate object
			System.out.print("Enter OTP: ");
			String otp = in.next();
			if(checkOTP(otp)){
				Registration.register(ac,ex);
				return true;//please replace acc with the object that you save info in it above
			}
		}
		return false;
	}

	public static WalletType getWalletType(){
		String walletType ="";
		System.out.println("Enter Wallet type(VFCash/Bank/Fawry): ");
		walletType = scanner.nextLine();
		if(walletType.equalsIgnoreCase("VFcash")){
			APICreator = new walletAPIFactoy();
			API = APICreator.createTransformAPI();
			return WalletType.VFCASH;
		}
		else if(walletType.equalsIgnoreCase("Bank")){
			APICreator = new walletAPIFactoy();
			API = APICreator.createTransformAPI();
			return WalletType.CIBWALLET;
		}
		else if(walletType.equalsIgnoreCase("Fawry")){
			APICreator = new walletAPIFactoy();
			API = APICreator.createTransformAPI();
			return WalletType.FAWRY;
		}
		else
		{
			System.out.println("Wrong wallet Type");
			return null;
		}
	}

	public static boolean preRegister(){
		String BankNumber ="" ;
		WalletType walletType = null;
		externalAccount  extAccount = null;
		System.out.println("Enter UserName: ");
		String username = scanner.nextLine();

		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		currentUser = new instaPayAccount(username,password);

		if(!validator.checkUser(currentUser)) {
			System.out.println("InValid User Data");
			return false;
		}
		// choose type of ex-Account
		System.out.println("Enter Account type(Bank/Wallet): ");
		accountType = scanner.nextLine();
		if(accountType.equalsIgnoreCase("wallet")){
			walletType = getWalletType();
			if (walletType == null) return false;
		}
		else if(accountType.equalsIgnoreCase("bank")){
			APICreator = new bankAPIFactory();
			API = APICreator.createTransformAPI();
			System.out.println("Enter Bank Account Number: ");
			BankNumber = scanner.nextLine();
		}
		else {
			System.out.println("not wallet nor bank");
			return false;
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
			return false;
		}


		// if BAnk take Mobile number / Bank Account
		// validate account / OTP Mobile
		// if Wallet take Mobile
		// validate mobile / OTP mobile

		return true;
	}

	public static boolean checkOTP(String otp) {
		 System.out.println("OTP Is Correct");
		 return true;
		//authentication.Authenticate(currentUser.getAccount().getAccountNumber()).equals(otp) ||
	}

	public void Transfer() {
		// TODO - implement System.Transfer
	}

	public void getAccount() {
		// TODO - implement System.getAccount
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {

		// Welcome, Message
		System.out.println("Welcome to InstPay OnlinePayment Platform");
		instaPayAccount ac1 = new instaPayAccount("abedo1","0123456789");
		ac1.Account = new walletAccount("300001",WalletType.VFCASH);
		instaPayAccount ac2 = new instaPayAccount("abedo2","0123456789");
		ac2.Account = new walletAccount("300004",WalletType.VFCASH);
		instaPayAccount ac3 = new instaPayAccount("abedo3","0123456789");
		ac2.Account = new bankAccount("200001","100000000");
		instaPayAccount ac4 = new instaPayAccount("abedo4","0123456789");
		ac2.Account = new bankAccount("200002","100000001");
		DB.AddAccount(ac1);
		DB.AddAccount(ac2);
		DB.AddAccount(ac3);
		DB.AddAccount(ac4);
		// Ask User to Login or to Register

		while (true) {
			// if Login
			do {
				System.out.println("1-Login\n2-Register\n>");
				String opStart = scanner.nextLine();
				if (opStart.matches("(?i)(Login|1)")) {
					if (Login()) break;
					else continue;
				} else if (opStart.matches("(?i)(Register|2)")) {
					if (preRegister()) break;
					else continue;
//				// take username / password
//
//				System.out.println("Enter UserName: ");
//				String username = scanner.nextLine();
//
//				System.out.println("Enter Password: ");
//				String password = scanner.nextLine();
//
//				//validator = new Validator();
//				currentUser = new instaPayAccount(username,password);
//
////				if(!validator.checkUser(currentUser)) {
////					System.out.println("InValid User Data");
////					continue;
////				}
//				// choose type of ex-Account
//				System.out.println("Enter Account type(Bank/Wallet): ");
//				String accountType = scanner.nextLine();
//				if(accountType.equalsIgnoreCase("wallet")){
//					System.out.println("Enter Wallet type(VFCash/Bank/Fawry): ");
//					 walletType = scanner.nextLine();
//					if(walletType.equalsIgnoreCase("VFcash")){
//						API = new VFCashAPI();
//					}
//					else if(walletType.equalsIgnoreCase("Bank")){
//						API = new bankWalletAPI();
//					}
//					else if(walletType.equalsIgnoreCase("Fawry")){
//						API = new paymentCompaniesAPI();
//					}
//					else
//					{
//						System.out.println("Wrong wallet Type");
//						continue;
//					}
//
//				}
//				else if(accountType.equalsIgnoreCase("bank")){
//					API = new bankAPI();
//					System.out.println("Enter Bank Account Number: ");
//					 BankNumber = scanner.nextLine();
//				}
//				else {
//					System.out.println("not wallet nor bank");
//					continue;
//				}
//				System.out.println("Enter Mobile Number: ");
//				String accountNumber = scanner.nextLine();
//
//				if(accountType.equalsIgnoreCase("bank")){
//					extAccount = new bankAccount(accountNumber,BankNumber);
//				}
//				else if(accountType.equalsIgnoreCase("wallet")){
//					extAccount = new walletAccount(accountNumber,walletType);
//				}
//
//				// OTP
//				if(!Signup(currentUser,extAccount,accountType)){
//					System.out.println("Error happen..Try Again");
//					continue;
//				}


					// if BAnk take Mobile number / Bank Account
					// validate account / OTP Mobile
					// if Wallet take Mobile
					// validate mobile / OTP mobile

//				break;
				}
				System.out.println("InValid Input");

			} while (true);
			do {
				currentUser.Account.displayMenu();
				String Choice = scanner.nextLine();
				if (Choice.equals("1")) {
					System.out.println("Enter UserName of Transfer Account: ");
					String transferUserName = scanner.nextLine();
					externalAccount t = DB.getUserExtrnalAccount(transferUserName);
					if (t == null || currentUser.Account.compare(t)) {
						System.out.println("Target Account Not Found Or you Choose to Transfer to your self");
						continue;
					}
					System.out.println("Enter Amount: ");
					double Amount = scanner.nextDouble();
					API.Transfer(currentUser.Account, t, Amount);

				} else if (Choice.equals("2")) {
					break;
				} else if (Choice.equals("3")) {

				} else if (Choice.equals("4")) {
					System.out.println("Your Balance IS: " + API.getBalance(currentUser.Account));

				} else if (Choice.equals("5") && accountType == "Bank") {

				}

				// Open to transfer or to LogOut
			} while (true);
		}
	}
}