package PaymentManagement;

public class BankInfo {
    private String accountNumber ;
    private String mobileNumber ;

    public BankInfo(String accountNumber, String mobileNumber) {
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
