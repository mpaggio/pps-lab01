package example.model;

public class SimpleBankAccountWithFee implements BankAccount{
    private final BankAccount bankAccount;
    private static final double WITHDRAWAL_FEE = 1.0;

    public SimpleBankAccountWithFee(final AccountHolder accountHolder, final double initialBalance) {
        try {
            this.bankAccount = new SimpleBankAccount(accountHolder, initialBalance);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public double getBalance() {
        return this.bankAccount.getBalance();
    }

    @Override
    public void deposit(int userID, double amount) {
        this.bankAccount.deposit(userID, amount);
    }

    @Override
    public void withdraw(int userID, double amount) {
        this.bankAccount.withdraw(userID, amount + WITHDRAWAL_FEE);
    }
}