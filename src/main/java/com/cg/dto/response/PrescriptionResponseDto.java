package com.cg.dto.response;

import com.cg.entity.User;
import com.cg.enums.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionResponseDto {
    private Long prescriptionId;
    private Long userId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private int fileSize;
    private LocalDateTime uploadedAt;
    private PrescriptionStatus status;
    private LocalDateTime reviewedAt;
    private String rejectionReason;
    private String notes;
}
