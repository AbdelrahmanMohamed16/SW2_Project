package PaymentManagement;
import User.*;
import java.util.ArrayList;
import java.util.HashMap;

public class bankAPI implements paymentAPI,TransferToBank {
	public static HashMap<externalAccount,Double> bankAccounts;
	static{
		bankAccounts = new HashMap<>() ;
		bankAccounts.put(new bankAccount( "200001", "100000000"),1000.0);
		bankAccounts.put(new bankAccount( "200002", "100000001"),2000.0);
		bankAccounts.put(new bankAccount( "200003", "100000002"),3000.0);
		bankAccounts.put(new bankAccount( "200004", "100000003"),10000.0);
		bankAccounts.put(new bankAccount("200005", "100000004"),20000.0);
		bankAccounts.put(new bankAccount("200006", "100000005"),500000.0);
		bankAccounts.put(new bankAccount("200007", "100000006"),1000000.0);
	}

	@Override
	public void Transfer(externalAccount src ,externalAccount dest ,double Amount){
		// transfer to wallet
		externalAccount srcObj = this.getAccount(src);
		externalAccount destObj = walletAPI.getAccount(dest);


		if( srcObj!=null&&destObj!=null && bankAccounts.get(srcObj)>=Amount)
		{
			// get src account from hash
			bankAccounts.put(srcObj,bankAccounts.get(srcObj)-Amount);
			// get dest wallet from hash
			walletAPI.walletAccounts.put(destObj,walletAPI.walletAccounts.get(destObj)+Amount);

			System.out.println("Transfer done");
		}
		else
		{
			System.out.println("transfer has Error or insufficient balance");
		}
	}

	@Override
	public double getBalance(externalAccount src) {
		for (externalAccount ac : bankAccounts.keySet()) {
			if(ac.compare(src))return bankAccounts.get(ac);
		}
		return -1;
	}

	public externalAccount getAccount(externalAccount src){
		for (externalAccount ac : bankAccounts.keySet()) {
			if(ac.compare(src)) return ac;
		}
		return null;
	}
	@Override
	public void payBill(externalAccount src , Bill bill){
		externalAccount srcObj = this.getAccount(src);

		if( srcObj!=null && bankAccounts.get(srcObj)>=bill.getBillAmount())
		{
			// get src account from hash
			bankAccounts.put(srcObj,bankAccounts.get(srcObj)-bill.getBillAmount());
			// get dest wallet from hash

			System.out.println("Bill paid Successfully done");
		}
		else
		{
			System.out.println("Bill has Error or insufficient balance");
		}
	}

	@Override
	public void TransferToBank( externalAccount src,externalAccount dest, double Amount){
		externalAccount srcObj = this.getAccount(src);
		externalAccount destObj = this.getAccount(dest);


		if( srcObj!=null && destObj!=null  && bankAccounts.get(srcObj)>=Amount)
		{
			// get src account from hash
			bankAccounts.put(srcObj,bankAccounts.get(srcObj)-Amount);
			// get dest wallet from hash
			bankAccounts.put(destObj,bankAccounts.get(destObj)+Amount);

			System.out.println("Transfer done");
		}
		else
		{
			System.out.println("transfer has Error or insufficient balance");
		}
	}

}