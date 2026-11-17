package com.example.order.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.order.domain.OrderStatus;
import com.example.order.dto.OrderResponse;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void 주문_조회_성공() throws Exception {
        given(orderService.get(1L)).willReturn(new OrderResponse(1L, "키보드", 2, OrderStatus.ORDERED));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("키보드"))
                .andExpect(jsonPath("$.status").value("ORDERED"));
    }

    @Test
    void 없는_주문_조회시_404() throws Exception {
        given(orderService.get(99L)).willThrow(new OrderNotFoundException(99L));

        mockMvc.perform(get("/api/orders/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void 수량이_0이면_400() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"productName": "키보드", "quantity": 0}
                                """))
                .andExpect(status().isBadRequest());
    }
}
