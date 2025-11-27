package ru.itk;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return "Order [product=" + product + ", cost=" + cost + "]";
    }
}

public class StreamNumberGenerationExample {

    public static void main(String[] args) {
        // 1. Create a list of order with different products and their prices
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        // 2. Group orders by products
        System.out.println("2. Group orders by products = " +
                orders.stream()
                        .collect(Collectors.groupingBy(Order::getProduct))
        );

        // 3. Find out total amount of all orders
        System.out.println("3. Sum of all orders = " +
                orders.stream().map(Order::getCost).reduce(0.0, Double::sum)
        );

        // 4. Sort products by total amount in descending order
        System.out.println("4. Sort products by total amount in descending order = " +
                orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
                        .entrySet().stream()
                        .sorted(Collections.reverseOrder(Comparator.comparingDouble(Map.Entry::getValue)))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ))
        );

        // 5. Choose three most expensive products
        System.out.println("5. Choose three most expensive products = " +
                orders.stream().sorted(Collections.reverseOrder(Comparator.comparingDouble(Order::getCost))).limit(3).toList()
        );

        // 6. Total amount of the three most expensive products
        System.out.println("6. Total amount of the three most expensive products = " +
                orders.stream().map(Order::getCost).sorted(Collections.reverseOrder()).limit(3).reduce(0.0, Double::sum)
        );

    }
}
