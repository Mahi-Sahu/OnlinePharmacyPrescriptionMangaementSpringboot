package com.cg.repository;

import com.cg.entity.MedicineTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineTagMappingRepository extends JpaRepository<MedicineTagMapping, Long> {
    @Query("""
            SELECT mtm
            FROM MedicineTagMapping mtm
            WHERE LOWER(mtm.tag.tagName) = LOWER(:tagName)
        """)
    List<MedicineTagMapping> findByTagName(String tagName);
}
