package com.cg.entity;

import com.cg.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "delivery_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryTracking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "delivery_tracking_id")
    private Long trackingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "delivery_partner")
    private String deliveryPartner;

    @Column(name = "estimated_delivery_date")
    private Date estimatedDelDate;

    @Column(name = "dispatched_at")
    private LocalDateTime dispatchedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_notes")
    private String deliveryNotes;

}
