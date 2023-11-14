package PaymentManagement;

public interface TransferToWallet {
    abstract void Transfer(String scAccNumber, String desAccNumber ,double Amount);

}
