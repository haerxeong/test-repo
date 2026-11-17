package com.example.order.exception;

import org.springframework.http.HttpStatus;

public class OrderCannotCancelException extends BusinessException {

    public OrderCannotCancelException(Long orderId) {
        super(HttpStatus.CONFLICT, "이미 배송된 주문은 취소할 수 없습니다. id=" + orderId);
    }
}
