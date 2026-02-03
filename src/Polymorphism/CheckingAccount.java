package Polymorphism;

public class CheckingAccount extends BankAccount {

    private float overDraftLimit;

    public CheckingAccount() {}

    public CheckingAccount(int accountNumber, Customer customer, float balance,
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
