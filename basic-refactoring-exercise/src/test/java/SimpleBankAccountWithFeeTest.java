import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccountWithFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleBankAccountWithFeeTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private static final double INITIAL_BALANCE = 0.0;
    private static final double NEGATIVE_INITIAL_BALANCE = -10.0;
    private static final double DEPOSIT_AMOUNT_EXAMPLE = 100.0;
    private static final double WITHDRAW_AMOUNT_EXAMPLE = 9.0;
    private static final double WITHDRAWAL_FEE = 1.0;

    @BeforeEach
    void setUp() {
        this.accountHolder = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new SimpleBankAccountWithFee(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, this.bankAccount.getBalance());
    }

    @Test
    void testWrongInitialBalance() {
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        assertThrows(IllegalArgumentException.class, () -> new SimpleBankAccountWithFee(accountHolder, NEGATIVE_INITIAL_BALANCE));
    }

    @Test
    void testWithdraw() {
        this.bankAccount.deposit(this.accountHolder.id(), DEPOSIT_AMOUNT_EXAMPLE);
        this.bankAccount.withdraw(this.accountHolder.id(), WITHDRAW_AMOUNT_EXAMPLE);
        assertEquals(DEPOSIT_AMOUNT_EXAMPLE - WITHDRAW_AMOUNT_EXAMPLE - WITHDRAWAL_FEE, this.bankAccount.getBalance());
    }
}
