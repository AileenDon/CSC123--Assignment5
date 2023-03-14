import java.util.ArrayList;

public class Bank {
    private static ArrayList<Account> accounts=new ArrayList<Account>();
    private static int accountNumbers=100;
    private Bank() {}

    public static Account openAccount(String firstName, String lastName, String email, String SSN, String accountType) {
        Person customer=new Person(firstName, lastName,email,SSN);
        Account account=new Account(accountNumbers++, accountType, customer);
        accounts.add(account);
        return account;
    }

    // Implement:
    public static Account openAccount(String firstName, String lastName, String email, String SSN, String accountType, double overdraftLimit) {
        Person customer = new Person(firstName, lastName, email, SSN);
        Account account = new Account(accountNumbers++, accountType, customer, overdraftLimit);
        accounts.add(account);
        return account;
    }

    public static Account getAccount(int accountNumber) {
        for(Account a: accounts) {
            if(a.getAccountNumber() == accountNumber) {
                return a;
            }
        }
        return null;
    }
    //

    public static Account printAccounts(int accountNumber) {
        for(Account a: accounts) {
            System.out.println(a);
        }
        return null;
    }

    //The following methods must be implemented
    /**public Account findAccount(int accountNumber) {

     1 - If the account exists then return Account object
     2 - If the account does not exist then return null
     }**/
    public static Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    /**public boolean deposit(int accountNumber, amount) {
     1 - Find account
     2 - If account not found then return false
     3 - If account found then deposit money and return the result of the deposit method

     }**/
    public static boolean deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        return account.deposit(amount);
    }

    /**public boolean withdraw(int accountNumber, amount) {
     1 - Find account
     2 - If account not found then return false
     3 - If account found then deposit money and return the result of the withdraw method
     }**/
    public static boolean withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        return account.withdraw(amount);
    }

    /**public boolean closeAccount(int accountNumber) {
     1 - Find account
     2 - If account not found then return false
     3 - If account found then close account and return true
     }**/
    public static boolean closeAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        accounts.remove(account);
        return true;
    }

    public static double getBalance(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return 0.0;
        }
        return account.getBalance();
    }

}
