package com.cg.service;

import com.cg.dto.request.SupportTicketRequestDto;
import com.cg.dto.response.SupportTicketResponseDto;

import java.util.List;

public interface SupportTicketService {
    List<SupportTicketResponseDto> getTicketByUserId(Long userId);
    SupportTicketResponseDto getTicketById(Long ticketId);
    List<SupportTicketResponseDto> getAllTickets();
    SupportTicketResponseDto createTicket(SupportTicketRequestDto supportTicketRequestDto);
    SupportTicketResponseDto updateTicket(Long ticketId,SupportTicketResponseDto supportTicketResponseDto);
    void deleteTicketById(Long ticketId);
}
