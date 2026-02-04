package Polymorphism;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Control BankAccountManagement.BankAccount management system
 * Provide menu interface to manage and operate accounts
 * @author Vu Cong Bui
 */
public class BankAccountDriver {

    public static Scanner input;
    public static BankAccount[] accounts;
    public static CongVuMethods myMethod;
    public static FileHandler fileHandler;

    public static Customer[] customers;

    /**
     * The main program
     */
    public void BankAccountProgram() {

        input = new Scanner(System.in);
        myMethod = new CongVuMethods();
        fileHandler = new FileHandler();
        getData();

        boolean exit = false;
        int choice;

        while (!exit) {

            choice = showMenu();

            if (choice == 9) {
                exit = true;
            } else {
                executeChoices(choice);
            }
        }
    }

    /**
     * Load data from a text file to test the program
     */
    public void getData() {

        customers = new Customer[10];
        customers[0] = new Customer(005, "Danny", "Vito", 'D');
        customers[1] = new Customer(004, "Amy", "Santiago", 'B');
        customers[2] = new Customer(003, "Marjorie", "Simpson", 'J');
        customers[3] = new Customer(001, "Tetsuo", "Shima", 'A');
        customers[4] = new Customer(002, "Jonas", "Khanwald", 'M');
        customers[5] = new Customer(006, "Pedro", "Pascal", 'B');
        customers[6] = new Customer(007, "Kaitlyn", "Olsen", 'D');

        accounts = new BankAccount[10];
        accounts[0] = new ChequingAccount(1005, customers[2], 76.57f, new Date(2019, 11, 5), 10.50f);
        accounts[1] = new SavingsAccount(1001, customers[0], 6500.50f, new Date(2023, 9, 7), 10f);
        accounts[2] = new SavingsAccount(1003, customers[2], 5533.57f, new Date(2019, 12, 6), 1.0f);
        accounts[3] = new SavingsAccount(1006, customers[4], 0.60f, new Date(2020, 7, 7), 10.0f);
        accounts[4] = new ChequingAccount(1002,customers[1], 2576.57f, new Date(2022, 9, 8), 10.50f);
        accounts[5] = new ChequingAccount(1008, customers[0], 200.10f, new Date(2023, 12, 8), 10.0f);
        accounts[6] = new SavingsAccount(1009, customers[2], 33.21f, new Date(2021, 5, 6), 1.0f);
        accounts[7] = new ChequingAccount(1010, customers[5], 500000.97f, new Date(2020, 11, 1 ), 100.0f);
        accounts[8] = new SavingsAccount(1004, customers[3], 3000.60f, new Date(2017, 10, 3), 10.0f);
        accounts[9] = new ChequingAccount(1007, customers[6], 9000.50f, new Date(2022, 11, 3), 50f);
    }

    /**
     * Show the options of the main menu
     * @return an integer value that represents user's selection
     */
    public int showMenu() {

        System.out.print("""
                +---------------------------------------------------+
                |             BANK ACCOUNT MANAGEMENT               |
                +---------------------------------------------------+
                | 1. Display all accounts                           |
                | 2. Display info for account by account number     |
                | 3. Display all customers                          |
                +---------------------------------------------------+
                | 4. Edit information                               |
                +---------------------------------------------------+
                | 5. Deposit                                        |
                | 6. Withdraw                                       |
                | 7. Transfer                                       |
                +---------------------------------------------------+
                | 8. Create a new account                           |
                +---------------------------------------------------+
                | 9. Exit                                           |
                +---------------------------------------------------+
                """);

        return myMethod.loopOption(1, 9);
    }

    /**
     * Execute the choices based on the user's selection
     * @param menuChoice - an integer value of user's selection from showMenu()
     */
    public void executeChoices(int menuChoice) {

        switch (menuChoice) {
            case 1 -> menuOption1();
            case 2 -> menuOption2();

            case 3 -> menuOption3();

            case 4 -> menuOption4();
            case 5 -> menuOption5();
            case 6 -> menuOption6();
            case 7 -> menuOption7();
            case 8 -> menuOption8();
        }
    }

    /**
     * Print all the existing user data
     */
    public void menuOption1() {

        System.out.print("ACCOUNTS LIST\n-----\n");
        System.out.println("Select account type");

        System.out.print("""
                1. Chequing Accounts
                2. Savings Accounts
                3. All Accounts
                -----
                """);

        int accountType = myMethod.loopOption(1, 3);

        switch (accountType) {

            case 1 -> {
                for (BankAccount account : accounts) {
                    if (account instanceof ChequingAccount) {
                        System.out.printf("Chequing#%d: %d %s\n", account.getAccountNumber(),
                                account.getCustomer().getCustomerID(), account.getCustomer().getFullName());
                    }
                }
            }

            case 2 -> {
                for (BankAccount account : accounts) {
                    if (account instanceof SavingsAccount) {
                        System.out.printf("Savings#%d: %d %s\n", account.getAccountNumber(),
                                account.getCustomer().getCustomerID(), account.getCustomer().getFullName());
                    }
                }
            }

            case 3 -> {
                for (BankAccount account : accounts) {
                    if (account instanceof SavingsAccount) {
                        System.out.printf("Savings#%d: %d %s\n", account.getAccountNumber(),
                                account.getCustomer().getCustomerID(), account.getCustomer().getFullName());
                    }
                    else {
                        System.out.printf("Chequing#%d: %d %s\n", account.getAccountNumber(),
                                account.getCustomer().getCustomerID(), account.getCustomer().getFullName());
                    }
                }
            }
        }

        System.out.printf("Sum of all balances: ");

        myMethod.waitForInput();
    }

    /**
     * Prompt user for an account number and print it
     */
    public void menuOption2() {

        System.out.print("Input account number: ");
        int accountNumber;
        boolean correctID = false;

        while (!correctID) {
            accountNumber = myMethod.loopInputInt();
            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {
                    System.out.print(account);
                    correctID = true;
                }
            }
            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }

        myMethod.waitForInput();
    }

    public void menuOption3() {

        System.out.println("ALL CUSTOMERS\n-----");

        for (Customer customer : customers) {
            System.out.printf("%d: %s\n", customer.getCustomerID(), customer.getFullName());
        }

        myMethod.waitForInput();
    }

    /**
     * Prompt user for an account number
     * Then show options to edit user basic information (BankAccountManagement.Name)
     */
    public void menuOption4() {

        System.out.print("----\nSelect account to edit\n");

        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("%d. %s\n", i + 1, accounts[i].getCustomer().getFullName());
        }

        int accChoice = myMethod.loopOption(1, accounts.length);

        boolean exitInfoEdit = false;
        int editChoice;

        while (!exitInfoEdit) {

            System.out.print("""
                    Edit information
                    1. CustomerID
                    2. First Name
                    3. Last Name
                    4. Middle Initial
                    5. Cancel
                    """);

            editChoice = myMethod.loopOption(1, 5);

            switch (editChoice) {

                case 1 -> {
                    System.out.print("Enter new customer ID: ");
                    accounts[accChoice - 1].getCustomer().setCustomerID(input.nextInt());
                    fileHandler.save(accounts);
                    System.out.println("Success!\n-----");
                }

                case 2 -> {
                    System.out.print("Enter new first name\n-> ");
                    accounts[accChoice - 1].getCustomer().setFirstName(input.nextLine());
                    fileHandler.save(accounts);
                    System.out.println("Success!\n-----");
                }
                case 3 -> {
                    System.out.print("Enter new last name\n-> ");
                    accounts[accChoice - 1].getCustomer().setLastName(input.nextLine());
                    fileHandler.save(accounts);
                    System.out.println("Success!\n-----");
                }
                case 4 -> {
                    System.out.print("Enter new middle initial\n-> ");
                    accounts[accChoice - 1].getCustomer().setMiddleInit(input.next().charAt(0));
                    fileHandler.save(accounts);
                    System.out.println("Success!\n-----");
                }
                case 5 -> exitInfoEdit = true;

            }
        }
        fileHandler.save(accounts);
        myMethod.waitForInput();
    }

    /**
     * Prompt user for an account number and an amount
     * Then deposit the amount into the user's account using BankAccountManagement.BankAccount.deposit()
     */
    public void menuOption5() {

        System.out.print("Input account number: ");
        int accountNumber;
        boolean correctID = false;

        while (!correctID) {

            accountNumber = myMethod.loopInputInt();

            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {

                    correctID = true;

                    System.out.print("Deposit amount: ");
                    float depositAmount = myMethod.loopPositiveFloat();

                    System.out.print("Transaction date: \n");
                    Date newDate = myMethod.editDate();

                    account.deposit(depositAmount, newDate);

                    System.out.printf("Successfully deposit $%.1f into account of %s!\n", depositAmount, account.getCustomer());
                }
            }

            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }
        fileHandler.save(accounts);
        myMethod.waitForInput();
    }

    /**
     * Prompt user for an account number and an amount
     * Then withdraw the amount from the user's account using BankAccountManagement.BankAccount.withdraw()
     */
    public void menuOption6() {

        System.out.print("Input account number: ");
        int accountNumber;
        boolean correctID = false;

        while (!correctID) {

            accountNumber = myMethod.loopInputInt();

            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {

                    correctID = true;

                    System.out.print("Withdraw amount: ");
                    float withdrawAmount = myMethod.loopPositiveFloat();

                    System.out.print("Transaction date: \n");
                    Date newDate = myMethod.editDate();

                    account.withdraw(withdrawAmount, newDate);
                }
            }

            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }
        fileHandler.save(accounts);
        myMethod.waitForInput();
    }

    /**
     * Prompt user for a sender and a receiver account numbers
     * Then transfer the amount from the user's account to another account using BankAccountManagement.BankAccount.transfer()
     */
    public void menuOption7() {

        int sender;
        int receiver;
        boolean correctID = false;

        while (!correctID) {

            System.out.print("Sender account number: ");
            sender = myMethod.loopInputInt();

            System.out.print("Receiver account number: ");
            receiver = myMethod.loopInputInt();

            for (BankAccount acc1 : accounts) {

                if (acc1.getAccountNumber() == sender) {

                    for (BankAccount acc2 : accounts) {

                        if (acc2.getAccountNumber() == receiver) {

                            correctID = true;

                            System.out.print("Transfer amount: ");
                            float transferAmount = myMethod.loopPositiveFloat();

                            System.out.print("Transaction date: \n");
                            Date newDate = myMethod.editDate();

                            acc1.transfer(transferAmount, acc2, newDate);
                        }
                    }
                }
            }

            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }
        fileHandler.save(accounts);
        myMethod.waitForInput();
    }

    /**
     * Prompt user for inputs to create a new BankAccountManagement.BankAccount instance
     * Expand the current array and add the new account to the array
     * Save the new accounts data to the database
     */
    public void menuOption8() {
        System.out.println("CREATE A NEW ACCOUNT");

        int newAccountNumber;
        boolean duplicate;

        do {
            duplicate = false;
            newAccountNumber = myMethod.changeAccountNumber();
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i].getAccountNumber() == newAccountNumber) {
                    System.out.println("Duplicated account number!");
                    duplicate = true;
                }
            }
        } while (duplicate);

        Customer newCustomer = myMethod.editCustomer();

        float newBalance = myMethod.editBalance();

        Date newDate = myMethod.editDate();

        BankAccount newAccount = new BankAccount(newAccountNumber, newCustomer, newBalance, newDate);

        BankAccount[] tempAccounts = Arrays.copyOf(accounts, accounts.length + 1);

        tempAccounts[tempAccounts.length - 1] = newAccount;

        accounts = tempAccounts;

        fileHandler.save(accounts);

        System.out.println("Success! New account was added to the database");
        myMethod.waitForInput();
    }

    public static void main(String[] args) {
        BankAccountDriver myProgram = new BankAccountDriver();
        myProgram.BankAccountProgram();
    }
}
