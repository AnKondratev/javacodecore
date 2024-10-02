package code.core.collections.bank.model;

import lombok.Data;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class BankAccount {

    private UUID walletId;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public BankAccount(UUID walletId, int balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public void deposit(int amount) {
        inputValidation(amount);
        lock.lock();
        try {
            this.setBalance(getBalance() + amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        inputValidation(amount);
        if (getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        lock.lock();
        try {
            this.setBalance(getBalance() - amount);
        } finally {
            lock.unlock();
        }
    }

    private void inputValidation(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "Identifier: " + walletId + "\n"
                + "Account balance: " + getBalance();
    }
}