package com.cg.controller;

import com.cg.dto.request.OrderRequestDto;
import com.cg.dto.response.OrderResponseDto;
import com.cg.dto.response.OrderStatusHistoryResponseDto;
import com.cg.enums.OrderStatus;
import com.cg.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new  ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        return new  ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<OrderResponseDto>> getActiveOrders() {
        return new  ResponseEntity<>(orderService.getActiveOrders(), HttpStatus.OK);
    }

    @GetMapping("/status-history")
    public ResponseEntity<List<OrderStatusHistoryResponseDto>> getStatusHistory(@RequestParam Long orderId) {
        return new ResponseEntity<>(orderService.getOrderHistory(orderId), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<OrderResponseDto>> getAllOrdersByStatus(@RequestParam OrderStatus orderStatus) {
        return new ResponseEntity<>(orderService.getOrderByStatus(orderStatus), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return new  ResponseEntity<>(orderService.createOrder(orderRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto>  updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequestDto) {
        return new  ResponseEntity<>(orderService.updateOrder(orderId, orderRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
