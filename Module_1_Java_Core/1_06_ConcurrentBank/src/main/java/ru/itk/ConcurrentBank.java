package ru.itk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {

    private final List<BankAccount> accounts = new ArrayList<BankAccount>();

    public BankAccount createAccount(BigDecimal initialBalance) {
        BankAccount account = new BankAccount(initialBalance);

        synchronized (accounts) {
            accounts.add(account);
        }

        return account;
    }

    public BankAccount createAccount(double initialBalance) {
        return this.createAccount(new BigDecimal(initialBalance));
    }

    public BankAccount createAccount(int initialBalance) {
        return this.createAccount(new BigDecimal(initialBalance));
    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) {

        BankAccount firstLocked = from.getId() > to.getId() ? from : to;
        BankAccount secondLocked = from.getId() > to.getId() ? to : from;

        firstLocked.getLock().lock();
        secondLocked.getLock().lock();

        try {
            from.withdraw(amount);
            to.deposit(amount);
        } finally {
            firstLocked.getLock().unlock();
            secondLocked.getLock().unlock();
        }
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        this.transfer(from, to, new BigDecimal(amount));
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        this.transfer(from, to, new BigDecimal(amount));
    }

    public BigDecimal getTotalBalance() {
        synchronized (accounts) {
            BigDecimal total = BigDecimal.ZERO;

            for (BankAccount account : accounts) {
                total = total.add(account.getBalance());
            }

            return total;
        }
    }
}
