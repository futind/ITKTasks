# Number generation
## Task
The goal was to use Stream API in order to solve the following tasks:
1. To create a list of orders with different products and their corresponding prices.
2. Group orders by products.
3. Find out total amount of all orders.
4. Sort products by total amount in descending order.
5. Choose three most expensive products.
6. Total amount of the three most expensive products.

## Code
```java
class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
}
```

## Launch
You would need to have **JDK 17** and **Apache Maven**.

Use the following commands:
```shell
mvn compile
mvn exec:java
```