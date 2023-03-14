//Aileen Dong (ydong8@toromail.csudh.edu)
public class Account {
    //Fields

    private static int accountNumber;
    private String type;
    private boolean accountOpen;
    private double balance;
    private Person accountHolder;
    private double overdraftLimit;
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

    public boolean withdraw(double amount) throws AccountClosedException, InsufficientBalanceException {
        if(!isOpen()) throw new AccountClosedException();
        if(amount > this.balance + overdraftLimit) throw new InsufficientBalanceException();
        this.balance = this.balance - amount;
        return true;
    }

    public boolean deposit(double amount) throws AccountClosedException {
        if(amount<0 || !isOpen()) throw new AccountClosedException();
        this.balance = this.balance + amount;
        return true;
    }

    public boolean isOpen() {
        return this.accountOpen;
    }


    public void close() throws Exception {
        System.out.println("Closing account " + this.accountNumber);
        // Add any necessary cleanup code here
    }


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

    class AccountClosedException extends Exception {
    }
}
