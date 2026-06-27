package com.cg.repository;

import com.cg.entity.Inventory;
import com.cg.enums.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findInventoryByStockStatus(StockStatus stockStatus);
    Optional<Inventory> findInventoryByInventoryId(Long id);

    @Query("""
        select i from Inventory i
        where i.medicine.medicineId=:medicineId
        """)
    Optional<Inventory> findInventoryByMedicineId(Long medicineId);
}
