package com.cg.controller;

import com.cg.dto.request.NotificationRequestDto;
import com.cg.dto.response.NotificationResponseDto;
import com.cg.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getUserNotifications(@RequestParam("userId") Long userId){
        return new ResponseEntity<>(notificationService.getUserNotifications(userId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/unread")
    public ResponseEntity<List<NotificationResponseDto>> getUnreadNotification(@PathVariable Long userId){
        return new ResponseEntity<>(notificationService.getUnreadNotificationOfUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NotificationResponseDto> createNotification(@RequestBody NotificationRequestDto notificationRequestDto){
        return new ResponseEntity<>(notificationService.createNotification(notificationRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<NotificationResponseDto> markNotificationAsRead(@RequestParam("notificationId") Long notificationId,
                                                                          @RequestParam("userId") Long userId){
        return new ResponseEntity<>(notificationService.markAsRead(userId, notificationId), HttpStatus.OK);
    }

    @DeleteMapping("{notificationId}")
    public ResponseEntity<NotificationResponseDto> deleteNotification(@PathVariable Long notificationId){
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
