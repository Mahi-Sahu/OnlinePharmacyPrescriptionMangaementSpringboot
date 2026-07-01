package com.cg.service;

import com.cg.dto.request.OrderRequestDto;
import com.cg.dto.response.OrderResponseDto;
import com.cg.dto.response.OrderStatusHistoryResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderById(Long orderId);
    List<OrderStatusHistoryResponseDto> getOrderHistory(Long orderId);
    List<OrderResponseDto> getActiveOrders();
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    void deleteOrderById(Long orderId);
    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);
}
