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
    private Long order_id;
    private String order_number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    private LocalDateTime order_date;

    @Enumerated(EnumType.STRING)
    private DeliverType delivery_type;
    private String delivery_slot;
    private Double subtotal_amount;
    private Double discount_amount;
    private Double delivery_charge;
    private Double total_amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod  payment_method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus  payment_status;

    @Enumerated(EnumType.STRING)
    private OrderStatus  order_status;
    private String cancel_reason;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToOne(mappedBy = "order")
    private DeliveryTracking deliveryTracking;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private  List<OrderStatusHistory> orderStatusHistory;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<SupportTicket>  supportTickets;
}
