package com.cg.dto.response;

import com.cg.entity.Order;
import com.cg.entity.Prescription;
import com.cg.entity.User;
import com.cg.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {
    private Long notificationId;
    private Long userId;
    private String title;
    private String message;
    private NotificationType notificationType;
    private Long relatedOrderId;
    private Long relatedPrescriptionId;
    private Integer isRead;
    private LocalDateTime createdAt;

}
