# Cancelable String Builder
## Task
The task was to implement a custom `StringBuilder` wrapper which
allows the user to undo the previous operation. Such a wrapper
has to be implemented while making use of a **"Memento"** 
programming pattern (GoF).

## Implementation
My implementation is rather simple. My wrapper is called 
`CancelableStringBuilder`, it has its own `StringBuilder` and it does
not implement the **Memento** pattern in full in order to avoid 
unnecessary complexity. It also utilizes **Command** pattern to
provide a versatile interface to string operations.

## Launch
To launch the application one would need **Apache Maven** and **JDK 17**.
```shell
mvn compile
mvn exec:java
```
