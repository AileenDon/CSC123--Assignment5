public class Account {
    //Fields

    private static int accountNumber;
    private String type;
    private boolean accountOpen;
    private double balance;
    private Person accountHolder;
    private Double overdraftLimit;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String SSN;


    //Constructor
    public Account(int accountNumber, String type, Person accountHolder, Double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.accountHolder = accountHolder;
        this.overdraftLimit=overdraftLimit;
        accountOpen=true;
    }

    public Account(int accountNumber, String type, Person accountHolder) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.accountHolder = accountHolder;
        accountOpen=true;
    }

    public boolean withdraw(double amount) {
        if(this.balance-amount<0) return false;
        this.balance=this.balance-amount;
        return true;
    }

    public boolean deposit(double amount) {
        if(amount<0 || !isOpen()) return false;
        this.balance=this.balance+amount;
        return true;
    }

    public boolean isOpen() {
        return this.accountOpen;
    }

/*    public void closeAccount() {
        this.accountOpen=false;
    }*/

    public double getBalance() {
        return this.balance;
    }

    public String toString() {
        return this.accountNumber+":"+type+":"+this.balance+":["+this.accountHolder.toString()+"]";
    }
    public int getAccountNumber() {return this.accountNumber;}

    // Implement:
    public String getType() {return this.type;}
    public  String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getSSN() {
        return this.SSN;
    }
    public String getAccountStatus() {
        return this.accountOpen ? "Account Open" : "Account Closed";
    }
    public Transaction[] getTransactions() {return this.getTransactions();}
}

