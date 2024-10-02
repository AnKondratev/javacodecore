package code.core.collections.bank.model;

import code.core.collections.bank.ConcurrentBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        ConcurrentBank bank = new ConcurrentBank();
        account = bank.createAccount(1000);
    }

    @Test
    public void testDeposit() {
        account.deposit(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        account.withdraw(300);
        assertEquals(700, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()
                -> account.withdraw(2000));
        assertEquals("Insufficient funds", thrown.getMessage());
    }

    @Test
    public void testDepositNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()
                -> account.deposit(-100));
        assertEquals("Amount must be greater than 0", thrown.getMessage());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()
                -> account.withdraw(-100));
        assertEquals("Amount must be greater than 0", thrown.getMessage());
    }

    @Test
    public void testToString() {
        String expectedString = "Identifier: "
                + account.getWalletId()
                + "\n" + "Account balance: "
                + account.getBalance();
        assertEquals(expectedString, account.toString());
    }
}

