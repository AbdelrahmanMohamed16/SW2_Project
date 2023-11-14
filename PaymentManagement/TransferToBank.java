package PaymentManagement;

public interface TransferToBank {
    abstract void Transfer(String scAccNumber,String scMobNumber, String desAccNumber,String decMobNumber ,double Amount);

}
