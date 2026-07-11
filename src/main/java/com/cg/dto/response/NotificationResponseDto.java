package com.cg.dto.response;

import com.cg.enums.NotificationType;
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
