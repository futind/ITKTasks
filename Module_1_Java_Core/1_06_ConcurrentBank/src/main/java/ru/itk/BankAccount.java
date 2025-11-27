package ru.itk;

import java.math.BigDecimal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private static final AtomicInteger nextId = new AtomicInteger(1);
    private final int id = nextId.getAndIncrement();
    private final ReentrantLock lock = new ReentrantLock();

    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public BankAccount(double balance) {
        this.balance = new BigDecimal(balance);
    }

    public BankAccount(int balance) {
        this.balance = new BigDecimal(balance);
    }

    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.add(amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        lock.lock();

        try {
            if (balance.compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }

            balance = balance.subtract(amount);
        } finally {
            lock.unlock();
        }
    }

    public int getId() {
        return id;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
