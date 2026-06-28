package com.cg.service.impl;

import com.cg.dto.request.MedicineRequestDto;
import com.cg.dto.response.MedicineResponseDto;
import com.cg.dto.response.PrescriptionMedicineResponseDto;
import com.cg.entity.Brand;
import com.cg.entity.Category;
import com.cg.entity.Medicine;
import com.cg.entity.PrescriptionMedicineMapping;
import com.cg.enums.ProductType;
import com.cg.repository.*;
import com.cg.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineAlternativesRepository medicineAlternativesRepo;
    private final MedicineRepository medicineRepo;
    private final CategoryRepository categoryRepo;
    private final BrandRepository brandRepo;
    private final ModelMapper modelMapper;
    private final PrescriptionRepository prescriptionRepo;

    public MedicineServiceImpl(MedicineAlternativesRepository medicineAlternativesRepo,
                               MedicineRepository medicineRepo,
                               CategoryRepository categoryRepo,
                               BrandRepository brandRepo,
                               ModelMapper modelMapper,
                               PrescriptionRepository prescriptionRepo) {
        this.medicineAlternativesRepo = medicineAlternativesRepo;
        this.medicineRepo = medicineRepo;
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
        this.prescriptionRepo = prescriptionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = medicineRepo.findAll();
        return medicines;
    }

    @Override
    public Medicine getMedicineById(Long medicineId) {
        Optional<Medicine> optional = medicineRepo.findById(medicineId);
        if(!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    @Override
    public List<Medicine> getMedicineByProductType(ProductType productType) {
        List<Medicine> list=medicineRepo.findByProductType(productType);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Medicine> getMedicineByBrandName(String brandName) {
        List<Medicine> list=medicineRepo.findByBrandName(brandName);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Medicine> getAllMedicineAlternatives(Long  medicineId) {
        List<Medicine> alternativeMedicines = medicineAlternativesRepo.findAlternativeMedicine(medicineId);
        if(alternativeMedicines.isEmpty()){
            return null;
        }
        return alternativeMedicines;
    }

    @Override
    public List<PrescriptionMedicineResponseDto> getMedicineByPrescriptionId(Long prescriptionId) {
        List<PrescriptionMedicineMapping> mappings =prescriptionRepo.findByPrescriptionId(prescriptionId);

        if (mappings.isEmpty()) {
            throw new RuntimeException("No medicines found for this prescription.");
        }

        return mappings.stream().map(mapping -> {
            Medicine medicine = mapping.getMedicine();
            PrescriptionMedicineResponseDto dto = new PrescriptionMedicineResponseDto();
            dto.setMedicineId(medicine.getMedicineId());
            dto.setMedicineName(medicine.getMedicineName());
            dto.setGenericName(medicine.getGenericName());

            dto.setUsageDescription(medicine.getUsageDescription());
            dto.setRestrictions(medicine.getRestrictions());
            dto.setImageUrl(medicine.getImageUrl());

            dto.setRequestedQuantity(mapping.getRequestedQuantity());
            dto.setDoctorNotes(mapping.getDoctorNotes());

            dto.setPrice(medicine.getPrice());
            dto.setDiscountPercentage(medicine.getDiscountPercentage());
            dto.setDosageNotes(medicine.getDosageNotes());
            dto.setPrescriptionRequired(medicine.getPrescriptionRequired());

            return dto;
        }).toList();
    }

    @Override
    public MedicineResponseDto createMedicine(MedicineRequestDto medicineRequestDto) {
        Category category = categoryRepo.findById(medicineRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepo.findById(medicineRequestDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));


        Medicine medicine = new Medicine();
        medicine.setCategory(category);
        medicine.setBrand(brand);
        medicine.setDescription(medicineRequestDto.getDescription());
        medicine.setPrice(medicineRequestDto.getPrice());
        medicine.setProductType(medicineRequestDto.getProductType());
        medicine.setMedicineName(medicineRequestDto.getMedicineName());
        medicine.setGenericName(medicineRequestDto.getGenericName());
        medicine.setProductType(medicineRequestDto.getProductType());
        medicine.setWellnessSegment(medicineRequestDto.getWellnessSegment());
        medicine.setDescription(medicineRequestDto.getDescription());
        medicine.setUsageDescription(medicineRequestDto.getUsageDescription());
        medicine.setDosageNotes(medicineRequestDto.getDosageNotes());
        medicine.setRestrictions(medicineRequestDto.getRestrictions());
        medicine.setPrice(medicineRequestDto.getPrice());
        medicine.setDiscountPercentage(medicineRequestDto.getDiscountPercentage());
        medicine.setImageUrl(medicineRequestDto.getImageUrl());
        medicine.setPrescriptionRequired(medicineRequestDto.getPrescriptionRequired());
        medicine.setMinimumAge(medicineRequestDto.getMinimumAge());
        medicine.setMaximumOrderQuantity(medicineRequestDto.getMaximumOrderQuantity());
        medicine.setIsActive(medicineRequestDto.getIsActive());

        medicine.setCreatedAt(LocalDateTime.now());
        medicine.setUpdatedAt(LocalDateTime.now());

        Medicine savedMedicine = medicineRepo.saveAndFlush(medicine);

        return modelMapper.map(savedMedicine, MedicineResponseDto.class);
    }

    @Override
    public MedicineResponseDto updateMedicine(Long medicineId, MedicineRequestDto medicineRequestDto) {
        Medicine medicine = medicineRepo.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        Category category = categoryRepo.findById(medicineRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepo.findById(medicineRequestDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        medicine.setCategory(category);
        medicine.setBrand(brand);
        medicine.setMedicineName(medicineRequestDto.getMedicineName());
        medicine.setGenericName(medicineRequestDto.getGenericName());
        medicine.setProductType(medicineRequestDto.getProductType());
        medicine.setWellnessSegment(medicineRequestDto.getWellnessSegment());
        medicine.setDescription(medicineRequestDto.getDescription());
        medicine.setUsageDescription(medicineRequestDto.getUsageDescription());
        medicine.setDosageNotes(medicineRequestDto.getDosageNotes());
        medicine.setRestrictions(medicineRequestDto.getRestrictions());
        medicine.setPrice(medicineRequestDto.getPrice());
        medicine.setDiscountPercentage(medicineRequestDto.getDiscountPercentage());
        medicine.setImageUrl(medicineRequestDto.getImageUrl());
        medicine.setPrescriptionRequired(medicineRequestDto.getPrescriptionRequired());
        medicine.setMinimumAge(medicineRequestDto.getMinimumAge());
        medicine.setMaximumOrderQuantity(medicineRequestDto.getMaximumOrderQuantity());
        medicine.setIsActive(medicineRequestDto.getIsActive());

        medicine.setUpdatedAt(LocalDateTime.now());

        Medicine updatedMedicine = medicineRepo.saveAndFlush(medicine);

        return modelMapper.map(updatedMedicine, MedicineResponseDto.class);
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        Medicine medicine = medicineRepo.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        medicine.setIsActive(0);
        medicine.setUpdatedAt(LocalDateTime.now());

        medicineRepo.saveAndFlush(medicine);
    }
}
