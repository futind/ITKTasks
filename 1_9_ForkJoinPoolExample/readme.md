# ForkJoinPoolException
## Task
The goal was to use ForkJoinPool in order to compute a factorial of a certain number. 

## Code
```java
public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
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