package com.cg.dto.response;

import com.cg.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistoryResponseDto {
    private Long historyId;
    private Long orderId;
    private String status;
    private String statusMessage;
    private UserResponseDto updatedBy;
    private LocalDateTime updatedAt;
}
