package com.cg.repository;

import com.cg.entity.DeliveryTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryTrackingRepository extends JpaRepository<DeliveryTracking, Long> {
    @Query("""
        SELECT dt from DeliveryTracking dt
        where dt.order.orderId=:orderId
        """)
    DeliveryTracking findByOrderId(Long orderId);
}
