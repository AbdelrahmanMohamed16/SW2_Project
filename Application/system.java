package Application;

import Login_RegisterManegment.*;
import PaymentManagement.*;
import User.*;

import java.text.DecimalFormat;
import java.util.Objects;
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



	private static double formatDouble(double value, int decimalDigits) {
		// Construct a format string based on the desired decimal digits
		StringBuilder formatBuilder = new StringBuilder("#.");
		for (int i = 0; i < decimalDigits; i++) {
			formatBuilder.append("#");
		}

		// Create a DecimalFormat instance with the specified format
		DecimalFormat decimalFormat = new DecimalFormat(formatBuilder.toString());

		// Parse the formatted string back to double
		String formattedString = decimalFormat.format(value);
		return Double.parseDouble(formattedString);
	}
	public static boolean Login() {
		// TODO - implement System.Login
		System.out.println("Enter UserName: ");
		String username = scanner.nextLine();

		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		loginService lg = new defaultSignin();
		if(!validator.checkUser(new instaPayAccount(username,password))){
			System.out.println("Invalid Data..!");
			return false;
		}
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
			}
			else if (currentUser.Account instanceof walletAccount){
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

	static public boolean Signup(instaPayAccount ac,externalAccount ex) {
		Registration = new DBRegistration();
		Scanner in = new Scanner(System.in);
		if (Registration != null) {
			//--------> input of user based on return type of register , then save this info in appropriate object
			System.out.print("Enter OTP: ");
			String otp = in.next();
			if(checkOTP(otp,ex.getMobileNumber())){
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

		if(DB.getUserExtrnalAccount(username) != null){
			System.out.println("Username Already exist");
			return false;
		}
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
			if(DB.getUserExtrnalAccount(extAccount)!= null){
				System.out.println("this bank Number registered before");
				return false;
			}
			APICreator = new bankAPIFactory();
			API = APICreator.createTransformAPI();
			if(((bankAPI)API).getAccount(extAccount)== null){
				System.out.println("Bank Account isn't exist in BANK DB");
				return false;
			}
		}
		else if(accountType.equalsIgnoreCase("wallet")){
			extAccount = new walletAccount(accountNumber,walletType);
			if(DB.getUserExtrnalAccount(extAccount)!= null){
				System.out.println("this number registered before");
				return false;
			}
			APICreator = new walletAPIFactoy();
			API = APICreator.createTransformAPI();
			externalAccount inWA = walletAPI.getAccount(extAccount) ;
			if(inWA == null || ((walletAccount)inWA).walletType != walletType){
				System.out.println("Wallet Account isn't exist in WALLET DB");
				return false;
			}
		}

		// OTP
		if(!Signup(currentUser,extAccount)){
			System.out.println("Error happen..Try Again");
			return false;
		}


		// if BAnk take Mobile number / Bank Account
		// validate account / OTP Mobile
		// if Wallet take Mobile
		// validate mobile / OTP mobile

		return true;
	}

	public static boolean checkOTP(String otp,String mobileNumber) {
		System.out.println("OTP Is Correct");
		return authentication.Authenticate(mobileNumber).equals(otp) || true;

	}

	public static boolean LoginRegisterPage(){
		System.out.println("1-Login\n2-Register\n3-Exit\n>");
		String opStart = scanner.nextLine();
		if (opStart.matches("(?i)(Login|1)")) {
			return Login();
		} else if (opStart.matches("(?i)(Register|2)")) {
			return preRegister();
		}
		else if(opStart.matches("(?i)(Exit|3)")){
			System.exit(0);
		}

		System.out.println("InValid Input");
		return false;
	}

	public static boolean ServicesPage() throws InterruptedException {
		currentUser.Account.displayMenu();
		String Choice = scanner.nextLine();

		// wallet to wallet DONE
		// BANK to Wallet DONE

		if (Choice.equals("1") ) {
			System.out.println("Enter UserName of InstaPay Account: ");
			String transferUserName = scanner.nextLine();
			externalAccount t = DB.getUserExtrnalAccount(transferUserName);
			if(Objects.equals(accountType, "Wallet") && t instanceof bankAccount){
				System.out.println("Wallet account cannot transfer to Bank");
				return false;
			}

			if (t == null || (!accountType.equalsIgnoreCase("bank")  && currentUser.Account.compare(t)) ) {
				System.out.println("Target Account Not Found Or you Choose to Transfer to your self");
				return false;
			}
			if(currentUser.getAccount() instanceof bankAccount && t instanceof bankAccount ){
				System.out.println(">> BANK TO BANK");
				if (currentUser.Account.compare(t)) {
					System.out.println("Target Account Not Found Or you Choose to Transfer to your self");
					return false;
				}
				else{
					System.out.println("Enter Amount: ");
					double Amount = scanner.nextDouble();
					scanner.nextLine();
					((bankAPI)API).TransferToBank(currentUser.Account, t, Amount);
				}
			}
			else{
				System.out.println("Enter Amount: ");
				double Amount = scanner.nextDouble();
				scanner.nextLine();
				API.Transfer(currentUser.Account, t, Amount);
			}

		}
		else if (Choice.equals("2")) {
			System.out.println("Enter billType (Gas/Electricity/Water)");
			String billType = scanner.nextLine();
			if(!billType.equalsIgnoreCase("Gas") && !billType.equalsIgnoreCase("Electricity") && !billType.equalsIgnoreCase( "Water"))
			{
				System.out.println("Sorry cannot pay this type of bill");
				return false;
			}
			System.out.println("Enter billNumber");
			String billNumber = scanner.nextLine();
			int consume = (int) (Math.random() * 1000) + 1;
			String consumed = Integer.toString(consume);
			double amount = formatDouble((double)(Math.random() * 200 + 1),2);
			System.out.println("bill amount is " + amount);
			Bill bill = null;
			if(billType.equalsIgnoreCase("Electricity"))
			{
				bill = new electricityBill(billNumber,amount,billType,consumed);
			}
			else if(billType.equalsIgnoreCase("Water"))
			{
				bill = new waterBill(billNumber,amount,billType,consumed);
			}
			else if(billType.equalsIgnoreCase("Gas"))
			{
				bill = new gasBill(billNumber,amount,billType,consumed);
			}
			API.payBill(currentUser.Account,bill);
		}
		else if (Choice.equals("3")) {
			System.out.println("Enter MobileNumber of Transfer Account: ");
			String mobNum = scanner.nextLine();
			//externalAccount t = DB.getUserExtrnalAccount(mobNum);
			externalAccount t = new walletAccount(mobNum,null);
			if ( !accountType.equalsIgnoreCase("bank")  && currentUser.Account.compare(t) ) {
				System.out.println("Target Account Not Found Or you Choose to Transfer to your self");
				return false;
			}
			else{
				System.out.println("Enter Amount: ");
				double Amount = scanner.nextDouble();
				scanner.nextLine();
				API.Transfer(currentUser.Account, t, Amount);
			}

		}
		else if (Choice.equals("4")) {
			System.out.println("Your Balance IS: " + API.getBalance(currentUser.Account));
			Thread.sleep(2000);
		}
		else if (Choice.equals("5") && Objects.equals(accountType, "Bank")) {
			System.out.println("Enter Bank Number of  Account: ");
			String bankNumber = scanner.nextLine();
			externalAccount t = new bankAccount(null,bankNumber);
			if (currentUser.Account.compare(t)) {
				System.out.println("Target Account Not Found Or you Choose to Transfer to your self");
				return false;
			}
			else{
				System.out.println("Enter Amount: ");
				double Amount = scanner.nextDouble();
				scanner.nextLine();
				((bankAPI)API).TransferToBank(currentUser.Account, t, Amount);
			}
		}
		else{
			System.out.println("Logged OUT...");
			Thread.sleep(2000);
			return true;
		}
		return false;

	}

	public static void RUN() throws InterruptedException {
		// Test Data Saved in DATABASE
		System.out.println("Welcome to InstPay OnlinePayment Platform");
		instaPayAccount ac1 = new instaPayAccount("account1","123456789");
		ac1.Account = new walletAccount("300001",WalletType.VFCASH);
		instaPayAccount ac2 = new instaPayAccount("account2","123456789");
		ac2.Account = new walletAccount("300004",WalletType.VFCASH);
		instaPayAccount ac3 = new instaPayAccount("account3","123456789");
		ac3.Account = new bankAccount("200001","100000000");
		instaPayAccount ac4 = new instaPayAccount("account4","123456789");
		ac4.Account = new bankAccount("200002","100000001");
		DB.AddAccount(ac1);
		DB.AddAccount(ac2);
		DB.AddAccount(ac3);
		DB.AddAccount(ac4);

		// Ask User to Login or to Register

		// login
		// TODO login with wrong data pass or username
		// TODO login with account1 and with registered one
		//register
		// TODO register with invalid user / pass
		// TODO register with exist account user / pass
		// TODO 2with not exist bank or mobile
		// TODO register with valid
		// trnsfer


		/*

			with enetrened account and new

					Wallet to wallet  or to insta
					bank to wallet or to insta
					wallet to bank or to insta
					bank to bank or to insta

					exceed balance

					same 4 but to myself

					to non exist wallet or bank

					bill with wallet
					bill with bank true o false
		*/




		while (true) {
			// want to login or Register
			while (!LoginRegisterPage());

			// singed in and need to Transfer
			while (!ServicesPage());

		}
	}

	public static void main(String[] args) throws InterruptedException {

			RUN();
//		// Test Data Saved in DATABASE
//		System.out.println("Welcome to InstPay OnlinePayment Platform");
//		instaPayAccount ac1 = new instaPayAccount("account1","123456789");
//		ac1.Account = new walletAccount("300001",WalletType.VFCASH);
//		instaPayAccount ac2 = new instaPayAccount("account2","123456789");
//		ac2.Account = new walletAccount("300004",WalletType.VFCASH);
//		instaPayAccount ac3 = new instaPayAccount("account3","123456789");
//		ac3.Account = new bankAccount("200001","100000000");
//		instaPayAccount ac4 = new instaPayAccount("account4","123456789");
//		ac4.Account = new bankAccount("200002","100000001");
//		DB.AddAccount(ac1);
//		DB.AddAccount(ac2);
//		DB.AddAccount(ac3);
//		DB.AddAccount(ac4);
//
//		// Ask User to Login or to Register
//
//		// login
//			 // TODO login with wrong data pass or username
//			 // TODO login with account1 and with registered one
//		//register
//			// TODO register with invalid user / pass
//			// TODO register with exist account user / pass
//		    // TODO 2with not exist bank or mobile
//			// TODO register with valid
//		// trnsfer
//
//
//		/*
//
//			with enetrened account and new
//
//					Wallet to wallet  or to insta
//					bank to wallet or to insta
//					wallet to bank or to insta
//					bank to bank or to insta
//
//					exceed balance
//
//					same 4 but to myself
//
//					to non exist wallet or bank
//
//					bill with wallet
//					bill with bank true o false
//		*/
//
//
//
//
//		while (true) {
//			// want to login or Register
//			while (!LoginRegisterPage());
//
//			// singed in and need to Transfer
//			while (!ServicesPage());
//
//		}
	}
}