package com.cg.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {
    private Long userId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private int fileSize;
    private Long reviewedBy;
    private String rejectionReason;
    private String notes;
}
