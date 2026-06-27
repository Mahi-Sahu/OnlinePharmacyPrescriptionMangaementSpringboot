package com.cg.service;

import com.cg.dto.request.PrescriptionRequestDto;
import com.cg.dto.request.PrescriptionUpdateRequestDto;
import com.cg.dto.response.PrescriptionResponseDto;
import com.cg.enums.PrescriptionStatus;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionResponseDto> getPrescriptionByStatus(PrescriptionStatus status);
    PrescriptionResponseDto getPrescriptionById(Long prescriptionId);
    PrescriptionResponseDto createPrescription(PrescriptionRequestDto prescriptionRequestDto);
    PrescriptionResponseDto updatePrescription(Long prescriptionId, PrescriptionUpdateRequestDto prescriptionDto);
    void deletePrescription(Long prescriptionId);
}
