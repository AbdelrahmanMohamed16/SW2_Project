package PaymentManagement;

public class bankAPIFactory implements paymentFactory{

    @Override
    public paymentAPI createTransformAPI() {
        return new bankAPI();
    }
}
