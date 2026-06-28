package com.cg.dto.response;

import com.cg.entity.Medicine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

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
