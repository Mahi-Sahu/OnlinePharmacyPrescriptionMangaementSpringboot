package com.cg.dto.response;

import com.cg.enums.DeliverType;
import com.cg.enums.OrderStatus;
import com.cg.enums.PaymentMethod;
import com.cg.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private String orderNumber;
    private Long userId;
    private Long addressId;
    private Long prescriptionId;
    private LocalDateTime orderDate;
    private DeliverType deliveryType;
    private String deliverySlot;
    private Double subtotalAmount;
    private Double discountAmount;
    private Double deliveryCharge;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String cancelReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
