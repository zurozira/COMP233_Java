package BankAccountManagement;

import java.util.Scanner;

/**
 * Helper methods to replace repetitive actions
 */
public class CongVuMethods {

    public static Scanner input;

    public CongVuMethods() {
        input = new Scanner(System.in);
    }

    /**
     * Wait for user input
     * The purpose is to wait for user to take an action before the next code run (improve visibility)
     */
    public void waitForInput() {
        System.out.print("Press Enter to return to main menu...");
        input.nextLine();
    }

    /**
     * Loop until user inputs an integer
     * @return the first integer value that the user inputs
     */
    public int loopInputInt() {

        int number;
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                number = input.nextInt();
                input.nextLine();
                return number;
            }
            else {
                System.out.print("Wrong input type! Number only\n-> ");
                input.next();
            }
        }
        return 0;
    }

    /**
     * Loop until user inputs a positive float (for money)
     * @return the first positive float value that the user inputs
     */
    public float loopPositiveFloat() {
        float amount;
        while (input.hasNext()) {
            if (input.hasNextFloat()) {
                amount = input.nextFloat();
                input.nextLine();

                if (amount > 0) {
                    return amount;
                }
                System.out.print("Wrong input! Positive float only!\n-> ");
            }
            else {
                System.out.print("Wrong input type! Float only\n-> ");
                input.next();
            }
        }
        return 0;
    }

    /**
     * Prompt user for inputs to change the BankAccountManagement.Date
     * Have to be in (Year, Month, Day) order to check for leap years
     * @return new BankAccountManagement.Date instance
     */
    public Date changeDate() {

        Date date = new Date();

        int newYear;
        int newMonth;
        int newDay;

        System.out.print("Year: ");
        do {
            newYear = loopInputInt();
            if (!date.setYear(newYear)) {
                System.out.print("Wrong range (1900 - 2026)\n-> ");
            }
        } while (!date.setYear(newYear));

        System.out.print("Month: ");
        do {
            newMonth = loopInputInt();
            if (!date.setMonth(newMonth)) {
                System.out.print("Wrong range (1 - 12)\n-> ");
            }
        } while (!date.setMonth(newMonth));

        System.out.print("Day: ");
        do {
            newDay = loopInputInt();
            if (!date.setDay(newDay)) {
                System.out.print("Wrong range (1 - 31) / Check if the year is leap year!\n-> ");
            }
        } while (!date.setDay(newDay));

        return date;
    }

    /**
     *
     * Prompt user to enter and validates a new name with first name, last name and middle init
     * @return a new BankAccountManagement.Name instance
     */
    public Name changeName() {

        Name name = new Name();

        String firstName;
        String lastName;
        char middleInit;

        System.out.println("First name:");
        do {
            System.out.print("-> ");
            firstName = input.nextLine();
            if (firstName.isBlank()) {
                System.out.print("First name cannot be empty\n");
            }
            else {
                name.setFirstName(firstName);
            }
        } while (firstName.isBlank());

        System.out.println("Last name: ");
        do {
            System.out.print("-> ");
            lastName = input.nextLine();
            if (lastName.isBlank()) {
                System.out.print("Last name cannot be empty\n");
            }
            else {
                name.setLastName(lastName);
            }
        } while (lastName.isBlank());

        boolean correctFormat = false;
        System.out.println("Middle init: ");
        do {
            System.out.print("-> ");
            String str = input.nextLine();
            if (!str.isBlank()) {
                middleInit = str.charAt(0);
                if (str.length() > 1) {
                    System.out.println("Middle init must contains only 1 character");
                }
                else if (!Character.isLetter(middleInit)) {
                    System.out.print("Middle init must be a letter\n");
                }
                else {
                    name.setMiddleInit(middleInit);
                    correctFormat = true;
                }
            }
            else {
                System.out.println("Middle init cannot be empty\n");
            }

        } while (!correctFormat);

        return name;
    }

    /**
     * Prompt user to enter and loop until a valid 4-digit account number between 1001 and 9999 is provided
     * @return a valid account number in the range
     */
    public int changeAccountNumber() {
        int accountNumber;

        do {
            System.out.print("Account number: ");
            accountNumber = loopInputInt();
            if (accountNumber < 1001 || accountNumber > 9999) {
                System.out.print("Account number must has 4 digits\n");
            }
        } while (accountNumber < 1001 || accountNumber > 9999);

        return accountNumber;
    }

    /**
     * Prompts the user to enter a new positive balance for the account using loopPositiveFloat()
     * @return a positive float value
     */
    public float changeBalance() {
        System.out.print("Balance: ");
        return loopPositiveFloat();
    }

    /**
     * Prompt user for input and loop until user returns a correct input within the range (parameters)
     * @param min - the first option
     * @param max - the last option
     * @return an integer value of the correct input
     */
    public int loopOption(int min, int max) {

        System.out.print("-> ");

        int choice;

        do {
            choice = loopInputInt();
            if (choice < min || choice > max) {
                System.out.print("Incorrect selection!\n-> ");
            }
        } while (choice < min || choice > max);

        return choice;
    }
}
