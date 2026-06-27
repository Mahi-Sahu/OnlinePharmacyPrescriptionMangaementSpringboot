package com.cg.repository;

import com.cg.entity.Medicine;
import com.cg.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    List<Medicine> findByProductType(ProductType productType);

    @Query("""
        select m from Medicine m
        where lower(m.brand.brandName)=lower(:brandName) 
        """)
    List<Medicine> findByBrandName(@Param("brandName") String brandName);
}
