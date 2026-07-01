package com.cg.repository;

import com.cg.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("""
        select oi from OrderItem oi
        where oi.order.orderId=:orderId
    """)
    List<OrderItem> findByOrderId(Long orderId);
}
