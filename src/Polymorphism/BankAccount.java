package Polymorphism;


/**
 * Represents a single bank account with basic banking operations
 * @author Vu Cong Bui
 */
public abstract class BankAccount {

    private int accountNumber;
    private Customer customer;
    private float balance;
    private Date lastTransaction;


    public BankAccount() {
    }

    public BankAccount(int accountNumber, Customer customer, float balance, Date lastTransaction) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.lastTransaction = lastTransaction;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        if (accountNumber >= 0) {
            this.accountNumber = accountNumber;
        }
    }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public float getBalance() { return balance; }

    public void setBalance(float balance) { this.balance = balance; }

    public Date getLastTransaction() { return lastTransaction; }

    public void setLastTransaction(Date lastTransaction) { this.lastTransaction = lastTransaction; }

    /**
     * Deposit an amount into the account
     * @param amount - the float amount to be deposited
     * @param lastTransaction - the date of this transaction
     */
    public void deposit(float amount, Date lastTransaction) {
        if (amount > 0) {
            balance += amount;
            this.lastTransaction = lastTransaction;
        }
        else {
            System.out.println("Deposit failed!");
        }
    }

    /**
     * Withdraw an amount into the account
     * @param amount - the float amount to be withdrawn
     * @param lastTransaction - the date of this transaction
     */
    public abstract void withdraw(float amount, Date lastTransaction);

    /**
     * Transfer an amount from this account to another account
     * @param amount - the float amount to be transferred
     * @param receiver - the BankAccountManagement.BankAccount of the receiver
     * @param lastTransaction - the date of this transaction
     */
    public void transfer(float amount, BankAccount receiver, Date lastTransaction) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.deposit(amount, lastTransaction);
            this.lastTransaction = lastTransaction;
            System.out.println("Transfer successfully!");
        }
        else {
            System.out.println("Transfer failed!");
        }
    }
}
