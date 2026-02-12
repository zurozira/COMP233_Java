package Interfaces;

/**
 * Represent a savings account that accrues interest over a period of time
 * @author Vu Cong Bui
 */
public class SavingsAccount extends BankAccount{

    private float interestRate;

    public SavingsAccount() {}

    public SavingsAccount(int accountNumber, Customer customer, float balance,
                          Date lastTransaction, float interestRate)
    {
        super(accountNumber, customer, balance, lastTransaction);
        this.interestRate = interestRate;
    }

    public float getInterestRate() { return interestRate; }

    public void setInterestRate(float interestRate) { this.interestRate = interestRate; }

    /**
     * Calculate and apply the interest to the balance over a number of months
     * @param months - the number of months to accrue interest
     */
    public void accruesInterest(int months) {

        setBalance((float) (getBalance() * Math.pow(1 + interestRate / 100, months)));
    }

    /**
     * Withdraw an amount from the account if amount does not exceed the balance
     * @param amount - the float amount to be withdrawn
     * @param lastTransaction - the date of this transaction
     */
    @Override
    public void withdraw(float amount, Date lastTransaction) {

        if (amount > 0 && amount <= getBalance()) {

            setBalance(getBalance() - amount);
            setLastTransaction(lastTransaction);

        }
    }
}
