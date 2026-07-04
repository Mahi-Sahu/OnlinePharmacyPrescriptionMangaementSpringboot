package com.cg.repository;

import com.cg.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("""
        select n from Notification n
        where n.user.userId=:userId
        """)
    List<Notification> findNotificationByUserId(@Param("userId") Long userId);

    @Query("""
        select n from Notification n
        where n.user.userId=:userId and n.isRead=:isRead
        """)
    List<Notification> findNotificationByUserId(@Param("userId") Long userId,@Param("isRead") Integer isRead);

    @Query("""
        select n from Notification n
        where n.notificationId=:notificationId
        and n.user.userId=:userId
        """)
    Notification findNotificationByIdAndUserId(@Param("notificationId") Long notificationId, @Param("userId") Long userId);
}
