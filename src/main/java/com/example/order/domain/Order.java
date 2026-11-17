package com.example.order.domain;

import com.example.order.exception.OrderCannotCancelException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {
    }

    private Order(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.status = OrderStatus.ORDERED;
    }

    public static Order create(String productName, int quantity) {
        return new Order(productName, quantity);
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
    }

    public void cancel() {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new OrderCannotCancelException(id);
        }
        this.status = OrderStatus.CANCELED;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
