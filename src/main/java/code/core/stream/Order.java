package code.core.stream;

import lombok.Data;

@Data
class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }
}

