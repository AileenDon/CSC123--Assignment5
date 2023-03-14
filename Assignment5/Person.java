//Aileen Dong (ydong8@toromail.csudh.edu)

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Person {

    //Fields
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String SSN;
    private int id;

    //Constructors
    public Person(String fName, String lName, String email, String SSN) {
        firstName=fName;
        lastName=lName;
        this.emailAddress=email;
        this.SSN=SSN;
        this.id=id;
    }

    //Methods


    @Override
    public String toString() {
        return id+":"+firstName+":"+lastName+":"+emailAddress+":"+SSN;
    }

    // Implement:
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            //  menu of options
            System.out.println("Please choose an option:");
            System.out.println("1 - Open a Checking account");
            System.out.println("2 - Open Saving Account");
            System.out.println("3 - List Accounts");
            System.out.println("4 - Account Statement");
            System.out.println("5 - Deposit funds");
            System.out.println("6 - Withdraw funds");
            System.out.println("7 - Close an account");
            System.out.println("8 - Save Transactions");
            System.out.println("9 - Exit");

            // Get user input
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // Open a checking account
                    System.out.println("Enter first name:");
                    String firstName = scanner.next();
                    System.out.println("Enter last name:");
                    String lastName = scanner.next();
                    System.out.println("Enter social security number:");
                    String ssn = scanner.next();
                    System.out.println("Enter overdraft limit:");
                    double overdraftLimit = scanner.nextDouble();
                    scanner.nextLine();

                    Account checkingAccount = Bank.openAccount(firstName, lastName, "", ssn, "Checking", overdraftLimit);
                    System.out.println("Thank you, the account number is " + checkingAccount.getAccountNumber());
                    break;

                case 2:// Open a saving account
                    System.out.println("Enter first name:");
                    String firstNameS = scanner.next();
                    System.out.println("Enter last name:");
                    String lastNameS = scanner.next();
                    System.out.println("Enter email address:");
                    String emailS = scanner.next();
                    System.out.println("Enter social security number:");
                    String ssnS = scanner.next();


                    Account savingAccount = Bank.openAccount(firstNameS, lastNameS, emailS, ssnS, "saving");
                    System.out.println("Thank you, the account number is " + savingAccount.getAccountNumber());
                    break;

                case 3:
                    // List account information
                    System.out.println("Enter account number:");
                    int accNum = scanner.nextInt();

                    Account acc = Bank.getAccount(accNum);
                    if (acc == null) {
                        System.out.println("Account not found");
                    } else {
                        System.out.println(acc.getAccountNumber() + "(" + acc.getType() + ") : " +
                                acc.getFirstName() + " : " + acc.getLastName() + " : " + acc.getSSN() + " : " + acc.getBalance() + " : " + acc.getAccountStatus());
                    }
                    break;

                case 4:
                    // Print account statement
                    System.out.println("Enter account number:");
                    int accountNumber = scanner.nextInt();

                    Account account = Bank.getAccount(accountNumber);
                    if (account != null) {
                        for (Transaction t : account.getTransactions()) {
                            String type = t.getType().equals("Credit") ? "Credit" : "Debit";
                            System.out.println(t.getId() + " : " + type + " : " + t.getAmount());
                        }
                        System.out.println("Balance: " + account.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    // Deposit funds
                    System.out.println("Enter account number:");
                    int accountNumberD = scanner.nextInt();
                    System.out.println("Enter amount to deposit:");
                    double amountD = scanner.nextDouble();
                    boolean depositSuccess = Bank.deposit(accountNumberD, amountD);

                    if (depositSuccess) {
                        System.out.println("Deposit successful. New balance: " + Bank.getBalance(accountNumberD));
                    } else {
                        System.out.println("Deposit failed. Invalid amount or account is closed.");
                    }
                    break;

                case 6:
                    // Withdraw funds

                    System.out.println("Enter account number:");
                    int accountNumberW = scanner.nextInt();

                    System.out.println("Enter amount to withdraw:");
                    double amountW = scanner.nextDouble();
                    boolean withdrawalSuccess = Bank.withdraw(accountNumberW, amountW);

                    if (withdrawalSuccess) {
                        System.out.println("Withdrawal successful. New balance: " + Bank.getBalance(accountNumberW));
                    } else {
                        System.out.println("Withdrawal failed. Insufficient balance.");
                    }
                    break;

                case 7:
                    //Close an account
                    System.out.println("Enter account number:");
                    accountNumber = scanner.nextInt();
                    boolean closed = Bank.closeAccount(accountNumber);
                    if (closed) {
                        System.out.println("Account closed successfully.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 8:
                    // Banking Application: Save transactions to file
                    try {
                        PrintWriter writer = new PrintWriter("transactions.txt");
                        System.out.println("Enter account number:");
                        accountNumber = scanner.nextInt();

                        account = Bank.getAccount(accountNumber);
                        if (account != null) {
                            for (Transaction t : account.getTransactions()) {
                                String type = t.getType().equals("Credit") ? "Credit" : "Debit";
                                writer.println(t.getId() + " : " + type + " : " + t.getAmount());
                            }
                            writer.println("Balance: " + account.getBalance());
                            System.out.println("Transactions saved to file.");
                        } else {
                            System.out.println("Account not found.");
                        }
                        writer.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                    break;

                case 9:
                    // If option 9 is selected, the program will exit
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}