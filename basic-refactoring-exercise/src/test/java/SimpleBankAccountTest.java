import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    private static final double INITIAL_BALANCE = 0.0;
    private static final double NEGATIVE_INITIAL_BALANCE = -100.0;
    private static final double CORRECT_DEPOSIT_AMOUNT_EXAMPLE = 100.0;
    private static final double WRONG_DEPOSIT_AMOUNT_EXAMPLE = 50.0;
    private static final double CORRECT_WITHDRAW_AMOUNT_EXAMPLE = 70.0;
    private static final int WRONG_ID = 2;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testWrongInitialBalance() {
        final AccountHolder localAccountHolder = new AccountHolder("Marco", "Paggetti", 2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SimpleBankAccount(accountHolder, NEGATIVE_INITIAL_BALANCE));
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        assertEquals(CORRECT_DEPOSIT_AMOUNT_EXAMPLE, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        bankAccount.deposit(WRONG_ID, WRONG_DEPOSIT_AMOUNT_EXAMPLE);
        assertEquals(CORRECT_DEPOSIT_AMOUNT_EXAMPLE, bankAccount.getBalance());
    }

    @Test
    void testNegativeDeposit() {
        bankAccount.deposit(accountHolder.id(), -CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        bankAccount.withdraw(accountHolder.id(), CORRECT_WITHDRAW_AMOUNT_EXAMPLE);
        assertEquals(CORRECT_DEPOSIT_AMOUNT_EXAMPLE - CORRECT_WITHDRAW_AMOUNT_EXAMPLE, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        bankAccount.withdraw(WRONG_ID, CORRECT_WITHDRAW_AMOUNT_EXAMPLE);
        assertEquals(CORRECT_DEPOSIT_AMOUNT_EXAMPLE, bankAccount.getBalance());
    }

    @Test
    void testNegativeWithdraw() {
        bankAccount.deposit(accountHolder.id(), CORRECT_DEPOSIT_AMOUNT_EXAMPLE);
        bankAccount.withdraw(accountHolder.id(), -CORRECT_WITHDRAW_AMOUNT_EXAMPLE);
        assertEquals(CORRECT_DEPOSIT_AMOUNT_EXAMPLE, bankAccount.getBalance());
    }
}
