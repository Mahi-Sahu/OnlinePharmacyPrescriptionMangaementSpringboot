package com.cg.controller;

import com.cg.dto.request.SupportTicketRequestDto;
import com.cg.dto.response.SupportTicketResponseDto;
import com.cg.service.SupportTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/support-ticket")
public class SupportTicketController {
    private final SupportTicketService supportTicketService;

    SupportTicketController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<SupportTicketResponseDto> getSupportTicketById(@PathVariable Long ticketId) {
        return new ResponseEntity<>(supportTicketService.getTicketById(ticketId), HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<List<SupportTicketResponseDto>> getSupportTicketByUserId(@RequestParam Long userId) {
        return new ResponseEntity<>(supportTicketService.getTicketByUserId(userId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SupportTicketResponseDto>> getAllTickets() {
        return new ResponseEntity<>(supportTicketService.getAllTickets(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupportTicketResponseDto> createSupportTicket(@RequestBody SupportTicketRequestDto supportTicketRequestDto) {
        return new ResponseEntity<>(supportTicketService.createTicket(supportTicketRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("{ticketId}")
    public ResponseEntity<SupportTicketResponseDto> updateTicket(@PathVariable Long ticketId, @RequestBody SupportTicketRequestDto supportTicketRequestDto) {
        return new ResponseEntity<>(supportTicketService.updateTicket(ticketId, supportTicketRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("{ticketId}")
    public ResponseEntity<SupportTicketResponseDto> deleteTicket(@PathVariable Long ticketId) {
        supportTicketService.deleteTicketById(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
