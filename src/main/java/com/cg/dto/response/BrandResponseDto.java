package com.cg.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandResponseDto {
    private Long brandId;
    private String brandName;
    private String description;
    private Integer isActive;
}
