package com.cg.service.impl;

import com.cg.dto.request.SupportTicketRequestDto;
import com.cg.dto.response.SupportTicketResponseDto;
import com.cg.entity.Order;
import com.cg.entity.SupportTicket;
import com.cg.entity.User;
import com.cg.repository.OrderRepository;
import com.cg.repository.SupportTicketRepository;
import com.cg.repository.UserRepository;
import com.cg.service.SupportTicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportTicketServiceImpl implements SupportTicketService {
    private final SupportTicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    SupportTicketServiceImpl(SupportTicketRepository ticketRepository,
                             OrderRepository orderRepository,
                             UserRepository userRepository,
                             ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<SupportTicketResponseDto> getTicketByUserId(Long userId) {
        List<SupportTicket> tickets = ticketRepository.findByUserId(userId);
        return tickets.stream().map(st->modelMapper.map(st,SupportTicketResponseDto.class)).toList();
    }

    @Override
    public SupportTicketResponseDto getTicketById(Long ticketId) {
        SupportTicket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found"));
        return modelMapper.map(ticket, SupportTicketResponseDto.class);
    }

    @Override
    public List<SupportTicketResponseDto> getAllTickets() {
        List<SupportTicket> supportTickets=ticketRepository.findAll();
        return supportTickets.stream().map(st->modelMapper.map(st,SupportTicketResponseDto.class)).toList();
    }

    @Override
    public SupportTicketResponseDto createTicket(SupportTicketRequestDto supportTicketRequestDto) {
        SupportTicket ticket=new  SupportTicket();
        Order order= orderRepository.findById(supportTicketRequestDto.getOrderId())
                        .orElseThrow(()->new RuntimeException("Order not found"));
        User user=userRepository.findById(supportTicketRequestDto.getUserId())
                        .orElseThrow(()->new RuntimeException("User not found"));
        User assignedUser=userRepository.findById(supportTicketRequestDto.getAssignedAdminId())
                        .orElseThrow(()->new RuntimeException("User not found"));
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setOrder(order);
        ticket.setDescription(supportTicketRequestDto.getDescription());
        ticket.setStatus(supportTicketRequestDto.getStatus());
        ticket.setUser(user);
        ticket.setPriority(supportTicketRequestDto.getPriority());
        ticket.setSubject(supportTicketRequestDto.getSubject());
        ticket.setAssignedAdmin(assignedUser);
        return modelMapper.map(ticketRepository.saveAndFlush(ticket), SupportTicketResponseDto.class);
    }

    @Override
    public SupportTicketResponseDto updateTicket(Long ticketId, SupportTicketResponseDto supportTicketResponseDto) {
        return null;
    }

    @Override
    public void deleteTicketById(Long ticketId) {

    }
}
