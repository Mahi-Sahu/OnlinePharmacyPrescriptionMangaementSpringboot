package com.cg.dto.request;

import com.cg.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDto {
    private Long userId;
    private String title;
    private String message;
    private NotificationType notificationType;
    private Long relatedOrderId;
    private Long relatedPrescriptionId;
    private Integer isRead;
}
