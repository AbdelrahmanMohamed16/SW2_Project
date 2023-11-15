package PaymentManagement;

import User.WalletType;

public interface paymentFactory {
	public static paymentFactory p = null;

	public paymentAPI createTransformAPI();

}