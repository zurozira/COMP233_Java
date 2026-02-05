package Polymorphism;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Driver class to control BankAccount management system
 * Provide menu interface to manage and operate accounts
 * @author Vu Cong Bui
 */
public class BankAccountDriver {

    public static Scanner input;
    public static BankAccount[] accounts;
    public static CongVuMethods myMethod;
    public static Customer[] customers;

    /**
     * The main program that initializes the system and runs menu loop
     */
    public void BankAccountProgram() {

        input = new Scanner(System.in);
        myMethod = new CongVuMethods();
        getData();

        boolean exit = false;
        int choice;

        while (!exit) {

            choice = showMenu();

            if (choice == 11) {
                exit = true;
            } else {
                executeChoices(choice);
            }
        }
    }

    /**
     * Populate the customers and accounts arrays with test data
     */
    public void getData() {

        customers = new Customer[7];
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
        accounts[3] = new SavingsAccount(1006, customers[4], 100f, new Date(2020, 7, 7), 10.0f);
        accounts[4] = new ChequingAccount(1002,customers[1], 2576.57f, new Date(2022, 9, 8), 10.50f);
        accounts[5] = new ChequingAccount(1008, customers[0], 200.10f, new Date(2023, 12, 8), 10.0f);
        accounts[6] = new SavingsAccount(1009, customers[1], 33.21f, new Date(2021, 5, 6), 1.0f);
        accounts[7] = new ChequingAccount(1010, customers[5], 500000.97f, new Date(2020, 11, 1 ), 100.0f);
        accounts[8] = new SavingsAccount(1004, customers[3], 3000.60f, new Date(2017, 10, 3), 10.0f);
        accounts[9] = new ChequingAccount(1007, customers[6], 9000.50f, new Date(2022, 11, 3), 50f);
    }

    /**
     * Display the main menu and prompt the user to select an option
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
                | 4. Display all accounts for a customer            |
                +---------------------------------------------------+
                | 5. Edit information                               |
                +---------------------------------------------------+
                | 6. Deposit                                        |
                | 7. Withdraw                                       |
                | 8. Transfer                                       |
                +---------------------------------------------------+
                | 9. Create a new account                           |
                +---------------------------------------------------+
                | 10. Calculate interest                            |
                +---------------------------------------------------+
                | 11. Exit                                          |
                +---------------------------------------------------+
                """);

        return myMethod.loopOption(1, 11);
    }

    /**
     * Execute the choices based on the user's selection
     * @param menuChoice - an integer value representing the selected menu option
     */
    public void executeChoices(int menuChoice) {

        switch (menuChoice) {
            case 1  -> menuOption1();
            case 2  -> menuOption2();
            case 3  -> menuOption3();
            case 4  -> menuOption4();
            case 5  -> menuOption5();
            case 6  -> menuOption6();
            case 7  -> menuOption7();
            case 8  -> menuOption8();
            case 9  -> menuOption9();
            case 10 -> menuOption10();
        }
    }

    /**
     * Display all accounts filtered by type (Chequing, Savings or All accounts)
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

        float sum = 0;

        for (BankAccount account : accounts) {

            switch (accountType) {

                case 1 -> {
                    if (account instanceof ChequingAccount cheqAcc) {
                        System.out.printf("Chequing#%d: #%d %s $%.2f\n", cheqAcc.getAccountNumber(),
                                cheqAcc.getCustomer().getCustomerID(), cheqAcc.getCustomer().getFullName(), cheqAcc.getBalance());

                        sum += account.getBalance();
                    }
                }

                case 2 -> {
                    if (account instanceof SavingsAccount savAcc) {
                        System.out.printf("Savings#%d : #%d %s $%.2f\n", savAcc.getAccountNumber(),
                                savAcc.getCustomer().getCustomerID(), savAcc.getCustomer().getFullName(), savAcc.getBalance());

                        sum += account.getBalance();
                    }
                }

                case 3 -> {
                    if (account instanceof SavingsAccount savAcc) {
                        System.out.printf("Savings#%d : #%d %s $%.2f\n", savAcc.getAccountNumber(),
                                savAcc.getCustomer().getCustomerID(), savAcc.getCustomer().getFullName(), savAcc.getBalance());

                    }
                    else {
                        System.out.printf("Chequing#%d: #%d %s $%.2f\n", account.getAccountNumber(),
                                account.getCustomer().getCustomerID(), account.getCustomer().getFullName(), account.getBalance());

                    }
                    sum += account.getBalance();
                }
            }

        }

        System.out.printf("-----\nSum of all balances: $%.2f\n-----\n", sum);

        myMethod.waitForInput();
    }

    /**
     * Display detailed information for a specific account by account number
     */
    public void menuOption2() {

        System.out.print("Input account number: ");
        int accountNumber;
        boolean correctID = false;

        while (!correctID) {
            accountNumber = myMethod.loopInputInt();
            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {

                    if (account instanceof SavingsAccount savAcc) {
                        System.out.printf("""
                                Account#: %d
                                Account type: Saving
                                Name: %s
                                Customer#: %d
                                Balance: $%.2f
                                Interest rate: %.1f
                                Last transaction: %s
                                -----
                                """, savAcc.getAccountNumber(), savAcc.getCustomer().getFullName(),
                                savAcc.getCustomer().getCustomerID(), savAcc.getBalance(),
                                savAcc.getInterestRate(), savAcc.getLastTransaction());
                    }

                    else {
                        System.out.printf("""
                                Account#: %d
                                Account type: Chequing
                                Name: %s
                                Customer#: %d
                                Balance: $%.2f
                                Overdraft limit: $%.2f
                                Last transaction: %s
                                -----
                                """, accountNumber, account.getCustomer().getFullName(),
                                account.getCustomer().getCustomerID(), account.getBalance(),
                                ((ChequingAccount) account).getOverDraftLimit(), account.getLastTransaction());
                    }

                    correctID = true;
                }
            }
            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }

        myMethod.waitForInput();
    }

    /**
     * Display all the existing customer in the database
     */
    public void menuOption3() {

        System.out.println("ALL CUSTOMERS\n-----");

        for (Customer customer : customers) {
            System.out.printf("#%d: %s\n", customer.getCustomerID(), customer.getFullName());
        }

        System.out.println("-----");
        myMethod.waitForInput();
    }

    /**
     * Display all the accounts belong to a customer
     */
    public void menuOption4() {

        System.out.println("ALL ACCOUNTS OF A CUSTOMER\n-----");

        for (Customer customer : customers) {
            System.out.printf("#%d: %s\n", customer.getCustomerID(), customer.getFullName());
        }
        System.out.println("-----");

        boolean correctCustomerID = false;

        while (!correctCustomerID) {
            System.out.print("Enter customer number: ");
            int customerNumber = myMethod.loopInputInt();

            for (BankAccount account : accounts) {

                if (account.getCustomer().getCustomerID() == customerNumber && account instanceof ChequingAccount cheqAcc) {

                    System.out.printf("""
                                Account#: %d
                                Account type: Chequing
                                Name: %s
                                Customer#: %d
                                Balance: $%.2f
                                Overdraft limit: $%.2f
                                Last transaction: %s
                                -----
                                """, cheqAcc.getAccountNumber(), cheqAcc.getCustomer().getFullName(),
                            cheqAcc.getCustomer().getCustomerID(), cheqAcc.getBalance(),
                            cheqAcc.getOverDraftLimit(), cheqAcc.getLastTransaction());

                    correctCustomerID = true;
                }

                else if (account.getCustomer().getCustomerID() == customerNumber && account instanceof SavingsAccount savAcc) {

                    System.out.printf("""
                                Account#: %d
                                Account type: Saving
                                Name: %s
                                Customer#: %d
                                Balance: $%.2f
                                Interest rate: %.1f
                                Last transaction: %s
                                -----
                                """, savAcc.getAccountNumber(), savAcc.getCustomer().getFullName(),
                            savAcc.getCustomer().getCustomerID(), savAcc.getBalance(),
                            savAcc.getInterestRate(), savAcc.getLastTransaction());

                    correctCustomerID = true;
                }
            }

            if (!correctCustomerID) {
                System.out.println("INCORRECT!");
            }
        }

        myMethod.waitForInput();
    }

    /**
     * Allow editing of customer information for a selected account
     * Prompt user for an account number
     * Then show options to edit user basic information
     */
    public void menuOption5() {

        System.out.print("----\nSelect account to edit\n");

        for (int i = 0; i < customers.length; i++) {
            System.out.printf("%d. %s #%d\n", i + 1, customers[i].getFullName(), customers[i].getCustomerID());
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
                    customers[accChoice - 1].setCustomerID(input.nextInt());
                    System.out.println("Success!\n-----");
                }

                case 2 -> {
                    System.out.print("Enter new first name\n-> ");
                    customers[accChoice - 1].setFirstName(input.nextLine());
                    System.out.println("Success!\n-----");
                }
                case 3 -> {
                    System.out.print("Enter new last name\n-> ");
                    customers[accChoice - 1].setLastName(input.nextLine());
                    System.out.println("Success!\n-----");
                }
                case 4 -> {
                    System.out.print("Enter new middle initial\n-> ");
                    customers[accChoice - 1].setMiddleInit(input.next().charAt(0));
                    System.out.println("Success!\n-----");
                }
                case 5 -> exitInfoEdit = true;

            }
        }

        myMethod.waitForInput();
    }

    /**
     * Process a deposit transaction for a specified account.
     * Prompt user for an account number and an amount
     * then deposit the amount into the user's account
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

                    System.out.print("Deposit amount: ");
                    float depositAmount = myMethod.loopPositiveFloat();

                    System.out.print("Transaction date: \n");
                    Date newDate = myMethod.editDate();

                    account.deposit(depositAmount, newDate);

                    System.out.printf("Successfully deposit $%.1f into account of %s!\n", depositAmount, account.getCustomer().getFullName());
                }
            }

            if (!correctID) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }

        myMethod.waitForInput();
    }

    /**
     * Process a withdrawal transaction for an account
     * Prompt user for an account number and an amount
     * then withdraw the amount from that account
     * Allow overdrafting if the account is a Chequing account
     */
    public void menuOption7() {

        System.out.println("WITHDRAW\n-----");

        System.out.print("Enter account number: ");
        int accountNumber;
        boolean correctAccNum = false;

        while (!correctAccNum) {

            accountNumber = myMethod.loopInputInt();

            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {

                    correctAccNum = true;

                    System.out.printf("Current balance: $%.2f\n", account.getBalance());

                    if (account instanceof ChequingAccount cheqAcc) {
                        System.out.printf("Overdraft limit: $%.2f\n", cheqAcc.getOverDraftLimit());
                    }

                    System.out.print("Withdraw amount: ");
                    float withdrawAmount = myMethod.loopPositiveFloat();

                    if (account instanceof ChequingAccount cheqAcc &&
                            withdrawAmount <= cheqAcc.getBalance() + cheqAcc.getOverDraftLimit())
                    {
                        System.out.print("Transaction date: \n");
                        Date newDate = myMethod.editDate();
                        cheqAcc.withdraw(withdrawAmount, newDate);
                        System.out.printf("""
                                Successfully withdraw $%.2f
                                New balance: $%.2f
                                Overdraft limit: $%.2f
                                """, withdrawAmount, account.getBalance(), cheqAcc.getOverDraftLimit());
                    }

                    else if (account instanceof SavingsAccount savAcc &&
                                withdrawAmount <= savAcc.getBalance())
                    {
                        System.out.print("Transaction date: \n");
                        Date newDate = myMethod.editDate();
                        savAcc.withdraw(withdrawAmount, newDate);
                        System.out.printf("""
                                Successfully withdraw $%.2f
                                New balance: $%.2f
                                """, withdrawAmount, account.getBalance());
                    }

                    else {
                        System.out.println("Not enough fund!");
                    }
                }
            }

            if (!correctAccNum) {
                System.out.print("Incorrect account number!\n-> ");
            }
        }

        myMethod.waitForInput();
    }

    /**
     * Process a transfer transaction between two account
     * Prompt user for a sender and a receiver account numbers
     * Then transfer the amount from the user's account to another account
     */
    public void menuOption8() {

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

        myMethod.waitForInput();
    }

    /**
     * Create a new bank account and adds it to the system.
     * Prompt user for inputs to create a new instance,
     * expand the current array and add the new account to the array and
     * save the new accounts data to the database
     */
    public void menuOption9() {
        System.out.println("CREATE A NEW ACCOUNT");

        int newAccountNumber;
        boolean duplicate;

        do {
            duplicate = false;
            newAccountNumber = myMethod.changeAccountNumber();
            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == newAccountNumber) {
                    System.out.println("Duplicated account number!");
                    duplicate = true;
                }
            }
        } while (duplicate);

        Customer newCustomer = myMethod.editCustomer();

        float newBalance = myMethod.editBalance();

        Date newDate = myMethod.editDate();

        System.out.print("""
                Select account type:
                1. Saving
                2. Chequing
                """);
        int accountType = myMethod.loopOption(1, 2);

        BankAccount newAccount;

        if (accountType == 1) {
            System.out.print("Interest Rate: ");
            float interestRate = myMethod.loopPositiveFloat();
            newAccount = new SavingsAccount(newAccountNumber, newCustomer, newBalance, newDate, interestRate);
        }
        else {
            System.out.print("Overdraft limit: ");
            float overdraftLimit = myMethod.loopPositiveFloat();
            newAccount = new ChequingAccount(newAccountNumber, newCustomer, newBalance, newDate, overdraftLimit);
        }

        BankAccount[] tempAccounts = Arrays.copyOf(accounts, accounts.length + 1);

        tempAccounts[tempAccounts.length - 1] = newAccount;

        accounts = tempAccounts;

        System.out.println("Success! New account was added to the database");
        myMethod.waitForInput();
    }

    /**
     * Calculate and apply interest to a savings account.
     * Prompt user for input an account number
     * if the account is a Savings account, calculate and apply interest to the balance
     */
    public void menuOption10() {
        System.out.println("CALCULATE INTEREST\n-----");

        int months;
        boolean correctAccNum = false;
        while (!correctAccNum) {

            System.out.print("Enter an account number: ");
            int accNumber = myMethod.loopInputInt();

            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accNumber && account instanceof SavingsAccount savingsAcc) {
                    correctAccNum = true;
                    System.out.print("Enter number of months: ");
                    months = myMethod.loopInputInt();

                    System.out.printf("Old balance: $%.2f\n", account.getBalance());
                    savingsAcc.accruesInterest(months);
                    System.out.printf("New balance after %d months of interest rate %.2f: $%.2f\n", months,
                            savingsAcc.getInterestRate(), savingsAcc.getBalance());
                }
            }
            if (!correctAccNum) {
                System.out.println("INCORRECT ACCOUNT NUMBER/TYPE!");
            }
        }

        myMethod.waitForInput();
    }

    public static void main(String[] args) {
        BankAccountDriver myProgram = new BankAccountDriver();
        myProgram.BankAccountProgram();
    }
}
