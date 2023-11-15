package PaymentManagement;

public class walletAPIFactoy implements paymentFactory{
    @Override
    public paymentAPI createTransformAPI() {
        return new walletAPI();
    }
}
