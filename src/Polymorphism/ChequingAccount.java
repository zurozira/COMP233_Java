package Polymorphism;

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
