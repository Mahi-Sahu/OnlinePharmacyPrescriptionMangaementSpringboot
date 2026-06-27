package com.cg.dto.response;

import com.cg.entity.Brand;
import com.cg.entity.Category;
import com.cg.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponseDto {
    private Long medicineId;

    private Category category;

    private Brand brand;

    private String medicineName;

    private String genericName;

    private ProductType productType;

    private String wellnessSegment;

    private String description;

    private String usageDescription;

    private String dosageNotes;

    private String restrictions;

    private Double price;

    private Double discountPercentage;

    private String imageUrl;

    private Integer prescriptionRequired;

    private Integer minimumAge;

    private Integer maximumOrderQuantity;

    private Integer isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
