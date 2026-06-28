package com.cg.repository;

import com.cg.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByBrandName(String brandName);

    @Query("""
        select distinct b from 
        Brand b JOIN Medicine m
        ON b.brandId= m.brand.brandId
        where m.category.categoryId=:categoryId
    """)
    List<Brand> findBrandByCategory(Long categoryId);
}
