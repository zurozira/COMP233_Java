package Polymorphism;

/**
 * Represent a chequing account with an overdraft limit.
 * Inherit from BankAccount
 * @author Vu Cong Bui
 */
public class ChequingAccount extends BankAccount {

    private float overDraftLimit;

    public ChequingAccount() {}

    public ChequingAccount(int accountNumber, Customer customer, float balance,
                           Date lastTransaction, float overDraftLimit)
    {
        super(accountNumber, customer, balance, lastTransaction);
        this.overDraftLimit = overDraftLimit;
    }

    public float getOverDraftLimit() { return overDraftLimit; }

    public void setOverDraftLimit(float overDraftLimit) { this.overDraftLimit = overDraftLimit; }

    /**
     * Withdraw an amount from the account and allow overdraft if the amount exceed the balance
     * @param amount - the float amount to be withdrawn
     * @param lastTransaction - the date of this transaction
     */
    @Override
    public void withdraw(float amount, Date lastTransaction) {

        if (amount > 0 && amount <= getBalance() + overDraftLimit) {

            if (amount <= getBalance()) {
                setBalance(getBalance() - amount);
            }
            else {
                float over = amount - getBalance();
                setBalance(0);
                overDraftLimit -= over;
            }

            setLastTransaction(lastTransaction);

        }
    }
}
