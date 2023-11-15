package PaymentManagement;

import User.WalletType;
import User.bankAccount;
import User.externalAccount;
import User.walletAccount;

import java.util.HashMap;
import java.util.List;

public class walletAPI implements paymentAPI {
	public static HashMap<externalAccount, Double> walletAccounts;
	static {
		walletAccounts = new HashMap<>() ;
		walletAccounts.put(new walletAccount( "300001", WalletType.VFCASH),1000.0);
		walletAccounts.put(new walletAccount( "300002", WalletType.CIBWALLET),2000.0);
		walletAccounts.put(new walletAccount( "300003", WalletType.FAWRY),3000.0);
		walletAccounts.put(new walletAccount( "300004", WalletType.VFCASH),10000.0);
		walletAccounts.put(new walletAccount("300005", WalletType.CIBWALLET),20000.0);
		walletAccounts.put(new walletAccount("300006", WalletType.FAWRY),500000.0);
		walletAccounts.put(new walletAccount("300007", WalletType.VFCASH),1000000.0);

	}


	static public externalAccount getAccount(externalAccount src){
		for (externalAccount ac : walletAccounts.keySet()) {
			if(ac.compare(src)) return ac;
		}
		return null;
	}

	@Override
	public void Transfer(externalAccount src , externalAccount dest , double Amount){
		externalAccount srcObj =  getAccount(src);
		externalAccount destObj = getAccount(dest);
		System.out.println(srcObj);
		System.out.println(destObj);


		if( srcObj!=null&&destObj!=null && walletAccounts.get(srcObj)>=Amount){
			// get src account from hash
			walletAccounts.put(srcObj,walletAccounts.get(srcObj)-Amount);
			// get dest wallet from hash
			walletAccounts.put(destObj,walletAccounts.get(destObj)+Amount);

			System.out.println("Transfer done");
		}
		else
		{
			System.out.println("transfer has Error or insufficient balance");
		}
	}
	@Override
	public double getBalance(externalAccount src) {
		for (externalAccount ac : walletAccounts.keySet()) {
			if(ac.compare(src))return walletAccounts.get(ac);
		}
		return -1;
	}

	@Override
	public void payBill(externalAccount src , Bill bill){
		externalAccount srcObj = getAccount(src);
		if( srcObj!=null && walletAccounts.get(srcObj)>=bill.getBillAmount())
		{
			// get src account from hash
			walletAccounts.put(srcObj,walletAccounts.get(srcObj)-bill.getBillAmount());
			// get dest wallet from hash

			System.out.println("Bill paid Successfully done");
		}
		else
		{
			System.out.println("Bill has Error or insufficient balance");
		}
	}
}