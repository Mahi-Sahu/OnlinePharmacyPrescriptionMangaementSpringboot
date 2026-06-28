package com.cg.repository;

import com.cg.entity.Prescription;
import com.cg.enums.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
    List<Prescription> findPrescriptionByStatus(PrescriptionStatus prescriptionStatus);
    @Query("""
        select p from Prescription p 
        where p.user.userId= :userId
                """)
    List<Prescription> findPrescriptionByUser(Long userId);
}
