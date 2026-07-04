package com.cg.service;

import com.cg.dto.request.NotificationRequestDto;
import com.cg.dto.response.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    List<NotificationResponseDto> getUserNotifications(Long userId);
    List<NotificationResponseDto> getUnreadNotificationOfUser(Long userId);
    NotificationResponseDto markAsRead(Long userId, Long notificationId);
    NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto);
    void deleteNotification(Long notificationId);
}
