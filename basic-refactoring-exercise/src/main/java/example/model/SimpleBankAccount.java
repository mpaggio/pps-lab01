package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        if (checkAmount(balance)) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Initial balance value must be positive! Given a negative one.");
        }
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID) && checkAmount(amount)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance -= amount;
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return checkAmount(amount) && this.balance >= amount;
    }

    private boolean checkUser(final int id) {
        return this.holder.id() == id;
    }

    private boolean checkAmount(final double amount) {
        return amount >= 0;
    }
}
