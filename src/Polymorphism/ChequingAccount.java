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

        if (amount > 0 && amount <= getBalance()) {

            setBalance(getBalance() - amount);
            setLastTransaction(lastTransaction);

            System.out.println("Withdraw successfully!");
        }
        else {
            System.out.println("Withdraw failed!");
        }
    }
}
