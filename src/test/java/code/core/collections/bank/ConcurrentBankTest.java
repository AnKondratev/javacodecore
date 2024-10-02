package code.core.collections.bank;

import code.core.collections.bank.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentBankTest {

    private ConcurrentBank bank;

    @BeforeEach
    void setUp() {
        bank = new ConcurrentBank();
    }

    @Test
    void testCreateAccount() {
        BankAccount account = bank.createAccount(100);
        assertNotNull(account);
        assertEquals(100, account.getBalance());
    }

    @Test
    void testTransfer_Success() {
        BankAccount account1 = bank.createAccount(200);
        BankAccount account2 = bank.createAccount(100);

        bank.transfer(account1, account2, 50);

        assertEquals(150, account1.getBalance());
        assertEquals(150, account2.getBalance());
    }

    @Test
    void testTransfer_NullAccount_ThrowsException() {
        BankAccount account = bank.createAccount(100);

        NullPointerException exception = assertThrows(NullPointerException.class, ()
                -> bank.transfer(account, null, 50));
        assertEquals("account1 or account2 are null", exception.getMessage());
    }

    @Test
    void testTransfer_NegativeAmount_ThrowsException() {
        BankAccount account1 = bank.createAccount(200);
        BankAccount account2 = bank.createAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()
                -> bank.transfer(account1, account2, -50));
        assertEquals("amount is negative", exception.getMessage());
    }

    @Test
    void testTransfer_ExceedsBalance_ThrowsException() {
        BankAccount account1 = bank.createAccount(200);
        BankAccount account2 = bank.createAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()
                -> bank.transfer(account1, account2, 300));
        assertEquals("amount is greater than balance", exception.getMessage());
    }

    @Test
    void testTotalBalance() {
        bank.createAccount(100);
        bank.createAccount(200);
        bank.createAccount(300);

        assertEquals(600, bank.totalBalance());
    }

    @Test
    void testGetCreateAccount() {
        BankAccount account = bank.getCreateAccount(150);
        assertNotNull(account);
        assertEquals(150, account.getBalance());
    }

    @Test
    void testGetTransfer() {
        BankAccount account1 = bank.createAccount(500);
        BankAccount account2 = bank.createAccount(300);

        bank.getTransfer(account1, account2, 200);

        assertEquals(300, account1.getBalance());
        assertEquals(500, account2.getBalance());
    }

    @Test
    void testGetTotalBalance() {
        bank.createAccount(100);
        bank.createAccount(200);

        assertEquals(300, bank.getTotalBalance());
    }
}

