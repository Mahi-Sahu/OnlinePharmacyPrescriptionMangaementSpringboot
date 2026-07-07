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
        return tickets.stream().map(st->{
            SupportTicketResponseDto dto=modelMapper.map(st,SupportTicketResponseDto.class);

            if(st.getAssignedAdmin()!=null){
                dto.setAssignedAdminId(st.getAssignedAdmin().getUserId());
            }
            return dto;
        }).toList();
    }

    @Override
    public SupportTicketResponseDto getTicketById(Long ticketId) {
        SupportTicket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found"));
        SupportTicketResponseDto dto=modelMapper.map(ticket, SupportTicketResponseDto.class);
        dto.setAssignedAdminId(ticket.getAssignedAdmin().getUserId());
        return dto;
    }

    @Override
    public List<SupportTicketResponseDto> getAllTickets() {
        List<SupportTicket> tickets=ticketRepository.findAll();
        return tickets.stream().map(st->{
            SupportTicketResponseDto dto=modelMapper.map(st,SupportTicketResponseDto.class);

            if(st.getAssignedAdmin()!=null){
                dto.setAssignedAdminId(st.getAssignedAdmin().getUserId());
            }
            return dto;
        }).toList();
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
    public SupportTicketResponseDto updateTicket(Long ticketId, SupportTicketRequestDto dto) {
        SupportTicket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found"));
        ticket.setUpdatedAt(LocalDateTime.now());
        if(ticket.getStatus()!=null)
            ticket.setStatus(dto.getStatus());
        if(dto.getAssignedAdminId()!=null){
            User assignedAdmin=userRepository.findById(dto.getAssignedAdminId())
                            .orElseThrow(()->new RuntimeException("User not found"));
            ticket.setAssignedAdmin(assignedAdmin);
        }

        return modelMapper.map(ticketRepository.saveAndFlush(ticket), SupportTicketResponseDto.class);
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        SupportTicket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
    }
}
