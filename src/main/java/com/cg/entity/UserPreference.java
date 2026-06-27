package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preference_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "refill_reminder_enabled")
    private Integer refillEnabled;

    @Column(name = "health_alert_enabled")
    private Integer healthEnabled;

    @Column(name = "email_notification_enabled")
    private Integer emailEnabled;

    @Column(name = "sms_notification_enabled")
    private Integer smsEnabled;

    @Column(name = "push_notification_enabled")
    private Integer pushNotiEnabled;

    @Column(name = "preferred_contact_method")
    private String contactMethod;

    private LocalDateTime updated_at;
}
