package com.example.order.service;

import com.example.order.domain.Order;
import com.example.order.dto.OrderCreateRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse create(OrderCreateRequest request) {
        Order order = orderRepository.save(Order.create(request.productName(), request.quantity()));
        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse get(Long orderId) {
        return OrderResponse.from(findOrder(orderId));
    }

    @Transactional
    public OrderResponse ship(Long orderId) {
        Order order = findOrder(orderId);
        order.ship();
        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse cancel(Long orderId) {
        Order order = findOrder(orderId);
        order.cancel();
        return OrderResponse.from(order);
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
