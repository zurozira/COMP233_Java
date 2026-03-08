package SongsProgram;

import java.util.Scanner;

/**
 * Helper methods to replace repetitive actions
 * Provide common input handling utilities for user interactions
 * @author Vu Cong Bui
 */
public class MyHelperMethods {

    private Scanner input;

    public MyHelperMethods() {
        input = new Scanner(System.in);
    }

    /**
     * Repeatedly prompts the user until a valid integer is entered
     * @return the first integer value that the user inputs
     */
    public int validIntegerInput()
    {
        int number = 0;
        boolean correctInput = false;
        while (!correctInput) {
            if (input.hasNextInt()) {
                number = input.nextInt();
                input.nextLine();
                correctInput = true;
            }
            else {
                System.out.print("Invalid input type! Integer only\n-> ");
                input.next();
            }
        }
        return number;
    }

    /**
     * Prompt user to select an option within a specified range
     * @param min - the first option
     * @param max - the last option
     * @return an integer value of the correct input within min - max range
     */
    public int optionSelector(int min, int max)
    {
        System.out.print("-> ");
        int choice;

        do {
            choice = validIntegerInput();
            if (choice < min || choice > max) {
                System.out.print("INVALID\n-> ");
            }
        } while (choice < min || choice > max);

        return choice;
    }

    /**
     * Wait for the user to press Enter to continue
     * This method prints a prompt and then wait for the user to press Enter
     */
    public void waitForInput()
    {
        System.out.print("Press Enter to return to the main menu...");
        input.nextLine();
    }
}
