package PaymentManagement;
import User.*;
public interface paymentAPI {
	abstract public double getBalance(externalAccount src);

	public void payBill(externalAccount src , Bill bill);
	abstract void Transfer(externalAccount src ,externalAccount dest ,double Amount);

}