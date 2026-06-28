package com.cg.repository;

import com.cg.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
        select count(o)>0 from Order o
        where o.prescription.prescriptionId=:prescriptionId
        """)
    boolean existsByPrescriptionId(Long prescriptionId);
}
