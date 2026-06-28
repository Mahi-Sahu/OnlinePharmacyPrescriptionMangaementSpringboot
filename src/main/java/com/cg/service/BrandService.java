package com.cg.service;

import com.cg.dto.request.BrandRequestDto;
import com.cg.dto.response.BrandResponseDto;

import java.util.List;

public interface BrandService {
    List<BrandResponseDto> getAllBrands();
    BrandResponseDto getBrandByName(String brandName);
    BrandResponseDto createBrand(BrandRequestDto brandRequestDto);
    BrandResponseDto updateBrand(Long brandId,BrandRequestDto brandRequestDto);
    void deleteBrand(Long id);
}
