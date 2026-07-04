package com.cg.service.impl;

import com.cg.dto.request.NotificationRequestDto;
import com.cg.dto.response.NotificationResponseDto;
import com.cg.entity.Notification;
import com.cg.entity.Order;
import com.cg.entity.Prescription;
import com.cg.entity.User;
import com.cg.repository.NotificationRepository;
import com.cg.repository.OrderRepository;
import com.cg.repository.PrescriptionRepository;
import com.cg.repository.UserRepository;
import com.cg.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final ModelMapper modelMapper;

    NotificationServiceImpl(NotificationRepository notificationRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            PrescriptionRepository prescriptionRepository,
                            ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NotificationResponseDto> getUserNotifications(Long userId) {
        List<Notification> notifications=notificationRepository.findNotificationByUserId(userId);
        return notifications.stream().map(notification -> {
            NotificationResponseDto dto =modelMapper.map(notification, NotificationResponseDto.class);
            dto.setUserId(notification.getUser().getUserId());

            if (notification.getRelatedOrder() != null) {
                dto.setRelatedOrderId(notification.getRelatedOrder().getOrderId());
            }
            if (notification.getRelatedPrescription() != null) {
                dto.setRelatedPrescriptionId(
                        notification.getRelatedPrescription().getPrescriptionId());
            }
            return dto;
        }).toList();
    }

    @Override
    public List<NotificationResponseDto> getUnreadNotificationOfUser(Long userId) {
        List<Notification> notifications=notificationRepository.findNotificationByUserId(userId,0);
        return notifications.stream().map(notification -> {
            NotificationResponseDto dto =modelMapper.map(notification, NotificationResponseDto.class);
            dto.setUserId(notification.getUser().getUserId());

            if (notification.getRelatedOrder() != null) {
                dto.setRelatedOrderId(notification.getRelatedOrder().getOrderId());
            }
            if (notification.getRelatedPrescription() != null) {
                dto.setRelatedPrescriptionId(
                        notification.getRelatedPrescription().getPrescriptionId());
            }
            return dto;
        }).toList();
    }

    @Override
    public NotificationResponseDto markAsRead(Long userId, Long notificationId) {
        Notification notification=notificationRepository.findNotificationByIdAndUserId(notificationId,userId);
        if(notification==null){
            throw new RuntimeException("Notification Not Found");
        }
        notification.setIsRead(1);
        notificationRepository.saveAndFlush(notification);
        return modelMapper.map(notification,NotificationResponseDto.class);
    }

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification=new Notification();
        User user=userRepository.findById(notificationRequestDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found!"));
        Order relatedOrder=orderRepository.findById(notificationRequestDto.getRelatedOrderId())
                        .orElseThrow(()->new RuntimeException("Order not found!"));
        Prescription relatedPrescription=prescriptionRepository.findById(notificationRequestDto.getRelatedPrescriptionId())
                        .orElseThrow(()->new RuntimeException("Prescription not found!"));

        notification.setUser(user);
        notification.setIsRead(0);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationType(notificationRequestDto.getNotificationType());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setTitle(notificationRequestDto.getTitle());
        notification.setRelatedOrder(relatedOrder);
        notification.setRelatedPrescription(relatedPrescription);

        notificationRepository.saveAndFlush(notification);
        return modelMapper.map(notification,NotificationResponseDto.class);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification=notificationRepository.findById(notificationId)
                .orElseThrow(()->new RuntimeException("Notification not found!"));
        notificationRepository.delete(notification);
    }
}
