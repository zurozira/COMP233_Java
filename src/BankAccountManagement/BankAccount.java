package BankAccountManagement;

/**
 * Represents a single bank account with basic banking operations
 */
public class BankAccount {

    private int accountNumber;
    private Name fullName;
    private float balance;
    private Date lastTransaction;

    public BankAccount() {
    }

    public BankAccount(int accountNumber, Name fullName, float balance, Date lastTransaction) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.balance = balance;
        this.lastTransaction = lastTransaction;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        if (accountNumber >= 1001) {
            this.accountNumber = accountNumber;
        }
    }

    public Name getName() {
        return fullName;
    }

    public void setName(Name fullName) {
        this.fullName = fullName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(Date lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    /**
     *
     * @param amount - the float amount to be deposited
     * @param lastTransaction - the date of this transaction
     * @return boolean value indicating if the transaction was successful or not
     */
    public boolean deposit(float amount, Date lastTransaction) {
        if (amount > 0) {
            balance += amount;
            this.lastTransaction = lastTransaction;
            return true;
        }
        return false;
    }

    /**
     *
     * @param amount - the float amount to be withdrawn
     * @param lastTransaction - the date of this transaction
     * @return boolean value indicating if the transaction was successful or not
     */
    public boolean withdraw(float amount, Date lastTransaction) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            this.lastTransaction = lastTransaction;
            return true;
        }
        System.out.println("Not enough fund!");
        return false;
    }

    /**
     *
     * @param amount - the float amount to be transferred
     * @param receiver - the BankAccountManagement.BankAccount of the receiver
     * @param lastTransaction - the date of this transaction
     * @return boolean value indicating if the transaction was successful or not
     */
    public boolean transfer(float amount, BankAccount receiver, Date lastTransaction) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.deposit(amount, lastTransaction);
            this.lastTransaction = lastTransaction;
            return true;
        }
        return false;
    }

    /**
     *
     * @return the full information of the account as a String block
     */
    public String toString() {
        return String.format("""
                Account number: %d
                Account owner: %s
                Balance: $%.1f
                Last transaction: %s
                -----
                """, accountNumber, fullName.toString(),
                balance, lastTransaction.toString());
    }

    /**
     * Returns the detail of the bank account in a format to be written to the test file
     * @return all of the information separated by spaces
     */
    public String writeAsRecord() {

        return String.format("%d %s %.2f %s", accountNumber, fullName.toString(), balance, lastTransaction.toString());
    }
}
