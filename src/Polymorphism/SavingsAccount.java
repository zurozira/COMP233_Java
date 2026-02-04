package Polymorphism;

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

    public void accruesInterest(int months) {

        setBalance((float) (getBalance() * Math.pow(1 + interestRate, months)));
    }

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
