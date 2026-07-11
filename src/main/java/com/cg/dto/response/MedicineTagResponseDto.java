package com.cg.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineTagResponseDto {
    private Long tagId;
    private String tagName;
    private String description;

    private Long medicineId;
    private String brandName;
    private String medicineName;
    private String genericName;
    private Double price;
}
