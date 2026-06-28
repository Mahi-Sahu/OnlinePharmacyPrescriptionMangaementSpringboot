package com.cg.dto.request;

import lombok.*;


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
