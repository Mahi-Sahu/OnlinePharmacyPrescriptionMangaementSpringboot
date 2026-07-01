package com.cg.dto.response;

import com.cg.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponseDto {
    private Long trackingId;
    private Long orderId;
    private String trackingNumber;
    private String deliveryPartner;
    private Date estimatedDelDate;
    private LocalDateTime dispatchedAt;
    private LocalDateTime deliveredAt;
    private DeliveryStatus deliveryStatus;
    private String deliveryNotes;
}
