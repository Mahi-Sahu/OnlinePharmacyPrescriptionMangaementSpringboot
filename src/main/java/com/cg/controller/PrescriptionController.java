package com.cg.controller;

import com.cg.dto.request.PrescriptionRequestDto;
import com.cg.dto.request.PrescriptionUpdateRequestDto;
import com.cg.dto.response.PrescriptionResponseDto;
import com.cg.enums.PrescriptionStatus;
import com.cg.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("status/{status}")
    public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionByStatus(@PathVariable("status") PrescriptionStatus prescriptionStatus){
        return new ResponseEntity<>(prescriptionService.getPrescriptionByStatus(prescriptionStatus), HttpStatus.OK);
    }

    @GetMapping("{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(@PathVariable Long prescriptionId){
        return new ResponseEntity<>(prescriptionService.getPrescriptionById(prescriptionId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(prescriptionService.getPrescriptionByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrescriptionResponseDto> createPrescription(@RequestBody PrescriptionRequestDto prescriptionRequestDto){
        return new ResponseEntity<>(prescriptionService.createPrescription(prescriptionRequestDto), HttpStatus.OK);
    }

    @PatchMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionResponseDto> updatePrescription(@PathVariable Long prescriptionId,
                                                                      @RequestBody PrescriptionUpdateRequestDto prescriptionDto){
        return new ResponseEntity<>(prescriptionService.updatePrescription(prescriptionId,prescriptionDto),HttpStatus.OK);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId){
        prescriptionService.deletePrescription(prescriptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
