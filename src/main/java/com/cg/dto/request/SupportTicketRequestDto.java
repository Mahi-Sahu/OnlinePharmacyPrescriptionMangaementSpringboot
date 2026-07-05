package com.cg.dto.request;

import com.cg.enums.PriorityType;
import com.cg.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportTicketRequestDto {
    private Long userId;
    private Long orderId;
    private String subject;
    private String description;
    private PriorityType priority;
    private TicketStatus status;
    private Long assignedAdminId;
}
