package code.core.collections.bank;

import code.core.collections.bank.model.BankAccount;
import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class ConcurrentBank {

    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(int balance) {
        UUID uuid = UUID.randomUUID();
        BankAccount account = new BankAccount(uuid, balance);
        accounts.put(uuid.toString(), account);
        return account;
    }

    public synchronized void transfer(BankAccount account1, BankAccount account2, int amount) {
        if (account1 == null || account2 == null) {
            throw new NullPointerException("account1 or account2 are null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("amount is negative");
        }
        if (amount > account1.getBalance()) {
            throw new IllegalArgumentException("amount is greater than balance");
        }
        account1.setBalance(account1.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);
    }

    public int totalBalance() {
        int totalBalance = 0;
        for (BankAccount account : accounts.values()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public BankAccount getCreateAccount(int balance) {
        return createAccount(balance);
    }

    public void getTransfer(BankAccount account1, BankAccount account2, int amount) {
        transfer(account1, account2, amount);
    }

    public int getTotalBalance() {
        return totalBalance();
    }
}