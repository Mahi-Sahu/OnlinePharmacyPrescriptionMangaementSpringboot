package com.cg.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandRequestDto {
    private String brandName;
    private String description;
    private Integer isActive;
}
