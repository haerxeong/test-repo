package com.example.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.order.domain.Order;
import com.example.order.domain.OrderStatus;
import com.example.order.dto.OrderCreateRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.repository.OrderRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// cancel() 테스트는 실습용으로 비워 두었습니다. PR에서 "@claude 테스트 추가해 줘"로 채워 보세요.
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void create_주문을_생성하면_ORDERED_상태다() {
        given(orderRepository.save(any(Order.class))).willAnswer(invocation -> invocation.getArgument(0));

        OrderResponse response = orderService.create(new OrderCreateRequest("키보드", 2));

        assertThat(response.productName()).isEqualTo("키보드");
        assertThat(response.quantity()).isEqualTo(2);
        assertThat(response.status()).isEqualTo(OrderStatus.ORDERED);
    }

    @Test
    void get_존재하지_않는_주문이면_OrderNotFoundException() {
        given(orderRepository.findById(99L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.get(99L))
                .isInstanceOf(OrderNotFoundException.class);
    }
}
