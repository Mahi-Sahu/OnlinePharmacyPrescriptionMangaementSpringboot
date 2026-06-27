package com.cg.service.impl;

import com.cg.dto.request.PrescriptionRequestDto;
import com.cg.dto.request.PrescriptionUpdateRequestDto;
import com.cg.dto.response.PrescriptionResponseDto;
import com.cg.entity.Prescription;
import com.cg.entity.User;
import com.cg.enums.PrescriptionStatus;
import com.cg.repository.PrescriptionRepository;
import com.cg.repository.UserRepository;
import com.cg.service.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final ModelMapper modelMapper;
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;

    PrescriptionServiceImpl(ModelMapper modelMapper, PrescriptionRepository prescriptionRepository,
                            UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<PrescriptionResponseDto> getPrescriptionByStatus(PrescriptionStatus status) {
        List<Prescription> prescriptions=prescriptionRepository.findPrescriptionByStatus(status);
        if(prescriptions.isEmpty())
            return null;
        List<PrescriptionResponseDto> prescriptionResponseDto=prescriptions.stream()
                .map(p->modelMapper.map(p,PrescriptionResponseDto.class)).toList();
        return prescriptionResponseDto;
    }

    @Override
    public PrescriptionResponseDto getPrescriptionById(Long prescriptionId) {
        Optional<Prescription> prescription=prescriptionRepository.findById(prescriptionId);
        if(prescription.isEmpty())
            return null;
        return modelMapper.map(prescription.get(),PrescriptionResponseDto.class);
    }

    @Override
    public PrescriptionResponseDto createPrescription(PrescriptionRequestDto prescriptionRequestDto) {
        User user= userRepository.findById(prescriptionRequestDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        User reviewedBy=userRepository.findById(prescriptionRequestDto.getReviewedBy())
                .orElseThrow(()->new RuntimeException("User not found"));
        Prescription prescription=new Prescription();
        prescription.setUser(user);
        prescription.setReviewedBy(reviewedBy);
        prescription.setFileName(prescriptionRequestDto.getFileName());
        prescription.setFileSize(prescriptionRequestDto.getFileSize());
        prescription.setNotes(prescriptionRequestDto.getNotes());
        prescription.setFileType(prescriptionRequestDto.getFileType());
        prescription.setFileUrl(prescriptionRequestDto.getFileUrl());
        prescription.setRejectionReason(prescriptionRequestDto.getRejectionReason());
        prescription.setReviewedAt(LocalDateTime.now());
        prescription.setStatus(PrescriptionStatus.PENDING);
        prescription.setUploadedAt(LocalDateTime.now());

        prescriptionRepository.saveAndFlush(prescription);
        return modelMapper.map(prescription,PrescriptionResponseDto.class);
    }

    @Override
    public PrescriptionResponseDto updatePrescription(Long prescriptionId, PrescriptionUpdateRequestDto prescriptionDto) {
        Prescription prescription=prescriptionRepository.findById(prescriptionId)
                .orElseThrow(()->new RuntimeException("Prescription not found"));
        if (prescriptionDto.getNotes() != null) {
            prescription.setNotes(prescriptionDto.getNotes());
        }

        if (prescriptionDto.getRejectionReason() != null) {
            prescription.setRejectionReason(prescriptionDto.getRejectionReason());
        }

        if (prescriptionDto.getStatus() != null) {
            prescription.setStatus(prescriptionDto.getStatus());
        }

        if (prescriptionDto.getReviewedBy() != null) {
            User admin = userRepository.findById(prescriptionDto.getReviewedBy())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            prescription.setReviewedBy(admin);
        }

        prescription.setReviewedAt(LocalDateTime.now());
        Prescription saved = prescriptionRepository.saveAndFlush(prescription);
        return modelMapper.map(saved, PrescriptionResponseDto.class);
    }

    @Override
    public void deletePrescription(Long prescriptionId) {
        Prescription prescription=prescriptionRepository.findById(prescriptionId)
                .orElseThrow(()->new RuntimeException("Prescription not found"));
        prescriptionRepository.delete(prescription);
    }
}
