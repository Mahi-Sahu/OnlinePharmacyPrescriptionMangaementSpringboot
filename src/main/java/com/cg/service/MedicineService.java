package com.cg.service;

import com.cg.dto.request.MedicineRequestDto;
import com.cg.dto.response.MedicineResponseDto;
import com.cg.dto.response.PrescriptionMedicineResponseDto;
import com.cg.entity.Medicine;
import com.cg.enums.ProductType;

import java.util.List;

public interface MedicineService {
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(Long medicineId);
    List<Medicine> getMedicineByProductType(ProductType productType);
    List<Medicine> getAllMedicineAlternatives(Long medicineId);
    List<Medicine> getMedicineByBrandName(String brandName);
    List<PrescriptionMedicineResponseDto> getMedicineByPrescriptionId(Long prescriptionId);
    MedicineResponseDto createMedicine(MedicineRequestDto medicine);
    MedicineResponseDto updateMedicine(Long medicineId, MedicineRequestDto medicineRequestDto);
    void deleteMedicine(Long medicineId);

}
