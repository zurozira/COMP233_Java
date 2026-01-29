package BankAccountManagement;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Control BankAccountManagement.BankAccount management system
 * Provide menu interface to manage and operate accounts
 */
public class BankAccountDriver {

    public static Scanner input;
    public static BankAccount[] accounts;
    public static CongVuMethods myMethod;
    public static FileHandler fileHandler;

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

            if (choice == 8) {
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

        accounts = FileHandler.getData("BATestData.txt");
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
                | 3. Edit information                               |
                +---------------------------------------------------+
                | 4. Deposit                                        |
                | 5. Withdraw                                       |
                | 6. Transfer                                       |
                +---------------------------------------------------+
                | 7. Create a new account                           |
                +---------------------------------------------------+
                | 8. Exit                                           |
                +---------------------------------------------------+
                """);

        return myMethod.loopOption(1, 8);
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
        }
    }

    /**
     * Print all the existing user data
     */
    public void menuOption1() {

        System.out.print("ACCOUNTS LIST\n-----\n");

        for (BankAccount account : accounts) {
            System.out.print(account);
        }

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

    /**
     * Prompt user for an account number
     * Then show options to edit user basic information (BankAccountManagement.Name)
     */
    public void menuOption3() {

        System.out.print("----\nSelect account to edit\n");

        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("%d. %s\n", i + 1, accounts[i].getName());
        }

        int accChoice = myMethod.loopOption(1, accounts.length);

        boolean exitInfoEdit = false;
        int editChoice;

        while (!exitInfoEdit) {

            System.out.print("""
                    Edit information
                    1. First Name
                    2. Last Name
                    3. Middle Initial
                    4. Cancel
                    """);

            editChoice = myMethod.loopOption(1, 4);

            switch (editChoice) {

                case 1 -> {
                    System.out.print("Enter new first name\n-> ");
                    accounts[accChoice - 1].getName().setFirstName(input.nextLine());
                    System.out.println("Success!\n-----");
                }
                case 2 -> {
                    System.out.print("Enter new last name\n-> ");
                    accounts[accChoice - 1].getName().setLastName(input.nextLine());
                    System.out.println("Success!\n-----");
                }
                case 3 -> {
                    System.out.print("Enter new middle initial\n-> ");
                    accounts[accChoice - 1].getName().setMiddleInit(input.next().charAt(0));
                    System.out.println("Success!\n-----");
                }
                case 4 -> exitInfoEdit = true;

            }
        }
        fileHandler.save(accounts);
        myMethod.waitForInput();
    }

    /**
     * Prompt user for an account number and an amount
     * Then deposit the amount into the user's account using BankAccountManagement.BankAccount.deposit()
     */
    public void menuOption4() {

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
                    Date newDate = myMethod.changeDate();

                    if (account.deposit(depositAmount, newDate)) {
                        System.out.printf("Successfully deposit $%.1f into account of %s!\n", depositAmount, account.getName());
                    } else {
                        System.out.print("Deposit failed!\n");
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
     * Prompt user for an account number and an amount
     * Then withdraw the amount from the user's account using BankAccountManagement.BankAccount.withdraw()
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

                    System.out.print("Withdraw amount: ");
                    float withdrawAmount = myMethod.loopPositiveFloat();

                    System.out.print("Transaction date: \n");
                    Date newDate = myMethod.changeDate();

                    if (account.withdraw(withdrawAmount, newDate)) {
                        System.out.print("Successfully withdraw $%.1f!\n");
                    } else {
                        System.out.print("Withdraw failed!\n");
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
     * Prompt user for a sender and a receiver account numbers
     * Then transfer the amount from the user's account to another account using BankAccountManagement.BankAccount.transfer()
     */
    public void menuOption6() {

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

                            System.out.print("Transaction date:\n");
                            Date newDate = myMethod.changeDate();

                            if (acc1.transfer(transferAmount, acc2, newDate)) {
                                System.out.printf("Successfully transfer $%.1f from %s to %s!\n",
                                        transferAmount, acc1.getName(), acc2.getName());
                            } else {
                                System.out.print("Transfer failed!\n");
                            }
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
    public void menuOption7() {
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

        Name newAccountName = myMethod.changeName();

        float newBalance = myMethod.changeBalance();

        Date newDate = myMethod.changeDate();

        BankAccount newAccount = new BankAccount(newAccountNumber, newAccountName, newBalance, newDate);

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
