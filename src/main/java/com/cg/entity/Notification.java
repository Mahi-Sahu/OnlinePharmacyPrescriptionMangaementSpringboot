package com.cg.entity;

import com.cg.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notification_type;

    @ManyToOne
    @JoinColumn(name = "related_order_id")
    private Order relatedOrder;

    @ManyToOne
    @JoinColumn(name = "related_prescription_id")
    private Prescription relatedPrescription;

    private Integer is_read;
    private LocalDateTime created_at;

}
