package PaymentManagement;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class bankAPI implements TransferToWallet,TransferToBank,paymentAPI {
     private static HashMap<BankInfo,Double> bankBalance;
	 static{
		 bankBalance = new HashMap<>() ;
		 bankBalance.put(new BankInfo("200001","100000000"),1000.0);
		 bankBalance.put(new BankInfo("200002","100000001"),1000.0);
		 bankBalance.put(new BankInfo("200003","100000002"),1000.0);
		 bankBalance.put(new BankInfo("200004","100000003"),1000.0);
		 bankBalance.put(new BankInfo("200005","100000004"),1000.0);
		 bankBalance.put(new BankInfo("200006","100000005"),1000.0);
		 bankBalance.put(new BankInfo("200007","100000006"),1000.0);
	 }
	/**
	 * 
	 * @param accountNumber
	 * @param mobileNumber
	 */
	public abstract boolean checkAccountExist(String accountNumber, String mobileNumber);

	public void Transfer(String scAccNumber, String desAccNumber, double Amount) {
		double balance = walletAPI.walletBalance.get(scAccNumber);
		if(walletAPI.checkAccountExist(desAccNumber)&&balance>=Amount)
		{
			walletAPI.walletBalance.put(scAccNumber,balance-Amount) ;
			balance = walletAPI.walletBalance.get(desAccNumber);
			walletAPI.walletBalance.put(desAccNumber,balance+Amount) ;
			System.out.println("Transfer done");
		}
		else
		{
			System.out.println("Transfer failed plz check your information");
		}
	}

	@Override
	public void Transfer(String scAccNumber, String scMobNumber, String desAccNumber, String decMobNumber, double Amount) {
		double balance = bankBalance.get(new BankInfo(scAccNumber,scMobNumber));
		if(checkAccountExist(desAccNumber,decMobNumber)&&balance>=Amount)
		{
			bankBalance.put(new BankInfo(scAccNumber,scMobNumber),balance-Amount) ;
			balance = bankBalance.get(new BankInfo(desAccNumber,decMobNumber));
			bankBalance.put(new BankInfo(desAccNumber,decMobNumber),balance+Amount) ;
			System.out.println("Transfer done");
		}
		else
		{
			System.out.println("Transfer failed plz check your information");
		}
	}
}