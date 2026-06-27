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
    private Long delTrackingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String tracking_number;
    private String delivery_partner;
    private Date estimated_delivery_date;
    private LocalDateTime dispatched_at;
    private LocalDateTime delivered_at;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus delivery_status;

    private String delivery_notes;

}
