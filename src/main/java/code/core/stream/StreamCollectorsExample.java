package code.core.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("TV", 2200.0),
                new Order("Smartphone", 820.0),
                new Order("TV", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Tablet", 700.0),
                new Order("Smartphone", 900.0),
                new Order("Smart watch", 620.0),
                new Order("Smart watch", 350.0)
        );

        Map<String, Double> groupingProducts = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));

        List<Map.Entry<String, Double>> sortedProducts = groupingProducts.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList();

        List<Map.Entry<String, Double>> topProducts = sortedProducts.stream()
                .limit(3)
                .toList();

        topProducts.forEach(System.out::println);
    }
}
