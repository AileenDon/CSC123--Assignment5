//Aileen Dong (ydong8@toromail.csudh.edu)

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static int accountNumbers = 100;

    private Bank() {}

    public static Account openAccount(String firstName, String lastName, String email, String SSN, String accountType) {
        Person customer = new Person(firstName, lastName, email, SSN);
        Account account = new Account(accountNumbers++, accountType, customer);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public static Account openAccount(String firstName, String lastName, String email, String SSN, String accountType, double overdraftLimit) {
        Person customer = new Person(firstName, lastName, email, SSN);
        Account account = new Account(accountNumbers++, accountType, customer, overdraftLimit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public static Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public static Account findAccount(int accountNumber) {
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            if (entry.getKey() == accountNumber) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static boolean deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        try {
            account.deposit(amount);
            return true;
        } catch (Account.AccountClosedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        try {
            account.withdraw(amount);
            return true;
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Account.AccountClosedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean closeAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        try {
            account.close();
            accounts.remove(accountNumber);
            return true;
        } catch (AccountClosedException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static double getBalance(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return 0.0;
        }
        return account.getBalance();
    }
}



