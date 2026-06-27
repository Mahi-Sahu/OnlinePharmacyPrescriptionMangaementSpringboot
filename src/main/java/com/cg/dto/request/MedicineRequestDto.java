package com.cg.dto.request;

import com.cg.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequestDto {

    private Long categoryId;

    private Long brandId;

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
}