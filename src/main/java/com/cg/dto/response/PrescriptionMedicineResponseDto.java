package com.cg.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicineResponseDto {
    private Long medicineId;
    private String medicineName;
    private String genericName;
    private String usageDescription;
    private String restrictions;
    private String imageUrl;

    private Integer requestedQuantity;
    private String doctorNotes;

    private Double price;
    private Double discountPercentage;
    private Integer prescriptionRequired;
    private String dosageNotes;
}
