# Concurrent Bank
## Task
The goal was to implement a Concurrent Bank. 
The system should support simultaneous deposits, withdrawals, and transfers 
between accounts. Each account has a unique number.

Implement the BankAccount class with methods deposit, withdraw, 
and getBalance that support multithreaded access.

Implement the ConcurrentBank class to manage accounts and perform transfers. 
The class should provide methods createAccount for creating a new account and transfer for performing transfers between accounts.

Transfers between accounts must be atomic, to avoid situations where one part 
of the transaction succeeds and the other fails.

Implement the method getTotalBalance, which returns the total balance of 
all accounts in the bank.

## Code
```java
public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}
```

## Launch
You would need to have **JDK 17** and **Apache Maven**.

Use the following commands:
```shell
mvn compile
mvn exec:java
```