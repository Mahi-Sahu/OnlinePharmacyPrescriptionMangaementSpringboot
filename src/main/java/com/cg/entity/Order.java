package com.cg.entity;

import com.cg.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    private DeliverType deliveryType;

    @Column(name = "delivery_slot")
    private String deliverySlot;

    @Column(name = "subtotal_amount")
    private Double subtotalAmount;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "delivery_charge")
    private Double deliveryCharge;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod  paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus  paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus  orderStatus;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "order")
    private DeliveryTracking deliveryTracking;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private  List<OrderStatusHistory> orderStatusHistory;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<SupportTicket>  supportTickets;
}
