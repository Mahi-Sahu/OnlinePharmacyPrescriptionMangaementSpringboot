package com.cg.service.impl;

import com.cg.dto.request.OrderRequestDto;
import com.cg.dto.response.OrderResponseDto;
import com.cg.dto.response.OrderStatusHistoryResponseDto;
import com.cg.entity.*;
import com.cg.enums.OrderStatus;
import com.cg.repository.*;
import com.cg.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final AddressRepository addressRepository;

    OrderServiceImpl(OrderRepository orderRepository,
                     ModelMapper modelMapper,
                     UserRepository userRepository,
                     AddressRepository addressRepository,
                     PrescriptionRepository prescriptionRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(o->modelMapper.map(o,OrderResponseDto.class)).toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order  order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found"));
        return modelMapper.map(order,OrderResponseDto.class);
    }

    @Override
    public List<OrderStatusHistoryResponseDto> getOrderHistory(Long orderId) {
        List<OrderStatusHistory> orderHistory = orderRepository.findOrderStatusHistoryByOrderId(orderId);
        if(orderHistory.isEmpty()){
            return null;
        }
        return orderHistory.stream().map(o->modelMapper.map(o,OrderStatusHistoryResponseDto.class)).toList();
    }

    @Override
    public List<OrderResponseDto> getActiveOrders() {
        List<OrderStatus> statuses = List.of(
                OrderStatus.CONFIRMED,
                OrderStatus.SHIPPED,
                OrderStatus.PACKED,
                OrderStatus.PRESCRIPTION_UNDER_REVIEW
        );
        List<Order> activeOrders = orderRepository.findByOrderStatusIn(statuses);

        if(activeOrders.isEmpty()){
            return null;
        }
        return activeOrders.stream().map(o->modelMapper.map(o,OrderResponseDto.class)).toList();
    }

    @Override
    public List<OrderResponseDto> getOrderByStatus(OrderStatus orderStatus) {
        List<Order> orders=orderRepository.findByOrderStatus(orderStatus);
        if(orders.isEmpty()){
            return null;
        }
        return orders.stream().map(o->modelMapper.map(o,OrderResponseDto.class)).toList();
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        User user=userRepository.findById(orderRequestDto.getUserId()).orElseThrow(()->new RuntimeException("User Not Found"));
        Prescription prescription=prescriptionRepository.findById(orderRequestDto.getPrescriptionId())
                .orElseThrow(()->new RuntimeException("Prescription Not Found"));
        Address address=addressRepository.findById(orderRequestDto.getAddressId())
                .orElseThrow(()->new RuntimeException("Address Not Found"));

        Order order=new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPrescription(prescription);
        order.setOrderDate(orderRequestDto.getOrderDate());
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderItems(orderRequestDto.getOrderItems());
        order.setOrderStatus(orderRequestDto.getOrderStatus());
        order.setOrderNumber(orderRequestDto.getOrderNumber());
        order.setCancelReason(orderRequestDto.getCancelReason());
        order.setDeliveryCharge(orderRequestDto.getDeliveryCharge());
        order.setDeliverySlot(orderRequestDto.getDeliverySlot());
        order.setDeliveryType(orderRequestDto.getDeliveryType());
        order.setDiscountAmount(orderRequestDto.getDiscountAmount());
        order.setPaymentMethod(orderRequestDto.getPaymentMethod());
        order.setPaymentStatus(orderRequestDto.getPaymentStatus());
        order.setSubtotalAmount(orderRequestDto.getSubtotalAmount());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.saveAndFlush(order);
        return modelMapper.map(order,OrderResponseDto.class);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId,OrderRequestDto orderRequestDto) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found"));
        if(orderRequestDto.getUserId()!=null){
            User user=userRepository.findById(orderRequestDto.getUserId()).orElseThrow(()->new RuntimeException("User Not Found"));
            order.setUser(user);
        }
        if(orderRequestDto.getAddressId()!=null){
            Address address=addressRepository.findById(orderRequestDto.getAddressId())
                    .orElseThrow(()->new RuntimeException("Address Not Found"));
            order.setAddress(address);
        }
        if(orderRequestDto.getPrescriptionId()!=null){
            Prescription prescription=prescriptionRepository.findById(orderRequestDto.getPrescriptionId())
                    .orElseThrow(()->new RuntimeException("Prescription Not Found"));
            order.setPrescription(prescription);
        }
        if(orderRequestDto.getOrderStatus()!=null){
            order.setOrderStatus(orderRequestDto.getOrderStatus());
        }
        if(orderRequestDto.getCancelReason()!=null){
            order.setCancelReason(orderRequestDto.getCancelReason());
        }
        if(orderRequestDto.getDeliveryCharge()!=null){
            order.setDeliveryCharge(orderRequestDto.getDeliveryCharge());
        }
        if(orderRequestDto.getDeliverySlot()!=null){
            order.setDeliverySlot(orderRequestDto.getDeliverySlot());
        }
        if(orderRequestDto.getDeliveryType()!=null){
            order.setDeliveryType(orderRequestDto.getDeliveryType());
        }
        if(orderRequestDto.getDiscountAmount()!=null){
            order.setDiscountAmount(orderRequestDto.getDiscountAmount());
        }
        if(orderRequestDto.getPaymentMethod()!=null){
            order.setPaymentMethod(orderRequestDto.getPaymentMethod());
        }
        if(orderRequestDto.getPaymentStatus()!=null){
            order.setPaymentStatus(orderRequestDto.getPaymentStatus());
        }
        if(orderRequestDto.getSubtotalAmount()!=null){
            order.setSubtotalAmount(orderRequestDto.getSubtotalAmount());
        }
        if(orderRequestDto.getTotalAmount()!=null){
            order.setTotalAmount(orderRequestDto.getTotalAmount());
        }
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.saveAndFlush(order);
        return modelMapper.map(order,OrderResponseDto.class);
    }
}
