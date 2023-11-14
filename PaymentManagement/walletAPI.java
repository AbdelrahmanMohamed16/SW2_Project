package PaymentManagement;

import java.util.HashMap;
import java.util.List;

public abstract class walletAPI implements paymentAPI , TransferToWallet {
	public static HashMap<String, Double> walletBalance;
	static {
		walletBalance = new HashMap<>();
		walletBalance.put("100001", 10000.0);
		walletBalance.put("100002", 10000.0);
		walletBalance.put("100003", 10000.0);
		walletBalance.put("100004", 10000.0);
		walletBalance.put("100005", 10000.0);
		walletBalance.put("100006", 10000.0);
		walletBalance.put("100007", 10000.0);
	}


	/**
	 * 
	 * @param mobileNumber
	 */
	public static boolean checkAccountExist(String mobileNumber)
	{
		return false;
	}

	@Override
	public void Transfer(String scAccNumber, String desAccNumber, double Amount) {
		double balance = walletBalance.get(scAccNumber);
		  if(checkAccountExist(desAccNumber)&&balance>=Amount)
		  {
			  walletBalance.put(scAccNumber,balance-Amount) ;
			  balance = walletBalance.get(desAccNumber);
			  walletBalance.put(desAccNumber,balance+Amount) ;
			  System.out.println("Transfer done");
		  }
		  else
		  {
			  System.out.println("Transfer failed plz check your information");
		  }
	}
	@Override
	public double getBalance(String accountNumber)
	{
		if(checkAccountExist(accountNumber))
		{
			return walletBalance.get(accountNumber);
		}
		return -1 ;
	}

	@Override
	public void payBill(Bill bill,String accountNumber) {
		  double billAmount = bill.getBillAmount();
		  if(checkAccountExist(accountNumber)&&billAmount<getBalance(accountNumber))
		  {
			  walletBalance.put(accountNumber,getBalance(accountNumber)-billAmount);
			  System.out.println("paying is successful");
		  }
		  else
		  {
			  System.out.println("paying failed plz check your information");

		  }
	}
}