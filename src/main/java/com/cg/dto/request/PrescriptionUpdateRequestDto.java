package com.cg.dto.request;

import com.cg.enums.PrescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionUpdateRequestDto {
        private Long reviewedBy;
        private PrescriptionStatus status;
        private String rejectionReason;
        private String notes;
}
