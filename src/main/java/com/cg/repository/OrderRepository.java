package com.cg.repository;

import com.cg.entity.Order;
import com.cg.entity.OrderStatusHistory;
import com.cg.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
        select count(o)>0 from Order o
        where o.prescription.prescriptionId=:prescriptionId
        """)
    boolean existsByPrescriptionId(Long prescriptionId);

    @Query("""
        select os from OrderStatusHistory os
        where os.order.orderId=:orderId
        """)
    List<OrderStatusHistory> findOrderStatusHistoryByOrderId(Long orderId);

    @Query("""
       SELECT o
       FROM Order o
       WHERE o.orderStatus IN :orderStatuses
       """)
    List<Order> findByOrderStatusIn(List<OrderStatus> orderStatuses);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    @Query("""
        select distinct o from Order o
        join o.orderItems oi
        join oi.medicine m
        where m.brand.brandId=:brandId
        """)
    List<Order> findByBrandId(Long brandId);
}
