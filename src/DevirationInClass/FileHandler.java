package DevirationInClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Read bank account information from a text file and write
 * updated account data back to the file system.
 */
public class FileHandler {

    private static BankAccount[] accounts;
    private static Scanner input;

    /**
     * This function accesses file specified and populates
     * the accounts property with an array of BankAccounts
     * @param fileName the name of the file
     */
    public static BankAccount[] getData(String fileName) {

        try {
            input = new Scanner(new File(fileName));

            int numAccounts = input.nextInt();

            accounts = new BankAccount[numAccounts];

            for (int i = 0; i < numAccounts; i++) {

                int accNum = input.nextInt();

                int cusID = input.nextInt();
                String firstN = input.next();
                char mInit = input.next().charAt(0);
                String lastN = input.next();

                float balance = input.nextFloat();

                int day = input.nextInt();
                int month = input.nextInt();
                int year = input.nextInt();

                accounts[i] = new BankAccount(accNum, new Customer(cusID, firstN, lastN, mInit), balance, new Date(day, month, year));
            }
        }
        catch (FileNotFoundException fNFE) {
            System.out.println("ERROR: FILE NOT FOUND (CHECK FILE SOURCE)");
        }
        catch (InputMismatchException iME) {
            System.out.println("ERROR: UNEXPECT DATA TYPE FOUND (CHECK FILE TO SEE IF DATA IS IN CORRECT ORDER)");
        }
        catch (IllegalStateException iSE) {
            System.out.println("ERROR: A METHOD HAS BEEN INVOKED AT AN ILLEGAL OR INAPPROPRIATE TIME");
        }
        catch (NoSuchElementException nSEE) {
            System.out.println("ERROR: AN ELEMENT DOESNT EXIST IN AN ITERATOR");
        }
        catch (Exception e) {
            System.out.println("ERROR: AN UNKNOWN ERROR OCCUR");
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * Writes an array of Bank Accounts to a text file
     * @param accountsToSave the array to be written
     */
    public void save(BankAccount[] accountsToSave) {

        try {
            FileWriter fw = new FileWriter("BATestData.txt", false);

            String numAccounts = String.valueOf(accountsToSave.length);

            fw.write(numAccounts);
            fw.write(System.lineSeparator());

            for (int i = 0; i < accountsToSave.length; i++) {
                fw.write(accountsToSave[i].writeAsRecord());
                fw.write(System.lineSeparator());
            }

            fw.close();
        }
        catch (IOException ioe) {
            System.out.print("UNKNOWN IO EXCEPTION OCCURRED");
            ioe.printStackTrace();
        }
    }
}

