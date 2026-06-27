package com.cg.repository;

import com.cg.entity.Medicine;
import com.cg.entity.MedicineAlternatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineAlternativesRepository extends JpaRepository<MedicineAlternatives, Long> {
    @Query("""
            SELECT ma.alternativeMedicine
           FROM MedicineAlternatives ma
           WHERE ma.medicine.medicineId = :medicineId
    """)
    List<Medicine> findAlternativeMedicine(@Param("medicineId") Long medicineId);
}
