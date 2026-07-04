package com.cg.controller;

import com.cg.dto.request.MedicineRequestDto;
import com.cg.dto.response.MedicineResponseDto;
import com.cg.dto.response.MedicineTagResponseDto;
import com.cg.dto.response.PrescriptionMedicineResponseDto;
import com.cg.enums.ProductType;
import com.cg.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    private final MedicineService medicineService;
    private final ModelMapper modelMapper;

    public MedicineController(MedicineService medicineService, ModelMapper modelMapper) {
        this.medicineService = medicineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponseDto>> getAllMedicines() {
        List<MedicineResponseDto> medicines= medicineService.getAllMedicines().stream()
                .map(c -> modelMapper.map(c, MedicineResponseDto.class))
                .toList();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> getMedicneById(@PathVariable Long medicineId) {
        MedicineResponseDto medicine=modelMapper.map(medicineService.getMedicineById(medicineId), MedicineResponseDto.class);
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @GetMapping("/product-type/{productType}")
    public ResponseEntity<List<MedicineResponseDto>> getMedicineByProductType(@PathVariable ProductType productType) {
        List<MedicineResponseDto> medicines=medicineService.getMedicineByProductType(productType).stream()
                .map((m-> modelMapper.map( m, MedicineResponseDto.class))).toList();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/{medicineId}/alternatives")
    public ResponseEntity<List<MedicineResponseDto>> getMedicineAlternatives(@PathVariable Long medicineId) {
        List<MedicineResponseDto> alternatives= medicineService.getAllMedicineAlternatives(medicineId).stream()
                .map(m-> modelMapper.map(m,MedicineResponseDto.class)).toList();
        return new ResponseEntity<>(alternatives,HttpStatus.OK);
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<MedicineResponseDto>> getMedicineByBrandName(@PathVariable String brandName) {
        List<MedicineResponseDto> medicines=medicineService.getMedicineByBrandName(brandName).stream()
                .map(m-> modelMapper.map(m,MedicineResponseDto.class)).toList();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/prescription/{prescriptionId}")
    public ResponseEntity<List<PrescriptionMedicineResponseDto>> getMedicineByPrescriptionId(@PathVariable Long prescriptionId) {
        return new ResponseEntity<>(medicineService.getMedicineByPrescriptionId(prescriptionId),HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<MedicineTagResponseDto>> getMedicineByTagName(@RequestParam String tagName) {
        return new ResponseEntity<>(medicineService.getMedicineByTagName(tagName),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicineResponseDto> createMedicine(@RequestBody MedicineRequestDto medicineRequestDto) {
        MedicineResponseDto medicineResponseDto=medicineService.createMedicine(medicineRequestDto);
        return new ResponseEntity<>(medicineResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<MedicineResponseDto> updateMedicine(@PathVariable Long medicineId,
                                                              @RequestBody MedicineRequestDto medicineRequestDto) {
        MedicineResponseDto medicineResponseDto=medicineService.updateMedicine(medicineId, medicineRequestDto);
        return new ResponseEntity<>(medicineResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable Long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
