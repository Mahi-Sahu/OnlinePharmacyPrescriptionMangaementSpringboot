package com.cg.dto.response;

import com.cg.enums.PriorityType;
import com.cg.enums.TicketStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportTicketResponseDto {
    private Long ticket_id;
    private Long userId;
    private Long orderId;
    private String subject;
    private String description;
    private PriorityType priority;
    private TicketStatus status;
    private Long assignedAdminId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
