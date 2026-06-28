package com.cg.service.impl;

import com.cg.dto.request.BrandRequestDto;
import com.cg.dto.response.BrandResponseDto;
import com.cg.entity.Brand;
import com.cg.repository.BrandRepository;
import com.cg.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    public final ModelMapper modelMapper;
    public final BrandRepository brandRepository;

    BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if(brands.isEmpty()){
            return null;
        }
        return brands.stream().map(b->modelMapper.map(b,BrandResponseDto.class)).toList();
    }

    @Override
    public BrandResponseDto getBrandByName(String brandName) {
        Brand brand= brandRepository.findByBrandName(brandName);
        if(brand == null){
            return null;
        }
        return modelMapper.map(brand,BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto createBrand(BrandRequestDto brandRequestDto) {
        Brand brand=new Brand();
        brand.setBrandName(brandRequestDto.getBrandName());
        brand.setDescription(brandRequestDto.getDescription());
        brand.setIsActive(brandRequestDto.getIsActive());
        brandRepository.saveAndFlush(brand);
        return modelMapper.map(brand,BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto updateBrand(Long brandId, BrandRequestDto brandRequestDto) {
        Brand brand=brandRepository.findById(brandId).orElseThrow(()-> new RuntimeException("Brand not found"));
        if(brandRequestDto.getBrandName() != null){
            brand.setBrandName(brandRequestDto.getBrandName());
        }
        if(brandRequestDto.getDescription() != null){
            brand.setDescription(brandRequestDto.getDescription());
        }
        if(brandRequestDto.getIsActive() != null){
            brand.setIsActive(brandRequestDto.getIsActive());
        }
        brandRepository.saveAndFlush(brand);
        return modelMapper.map(brand,BrandResponseDto.class);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand=brandRepository.findById(id).orElseThrow(()-> new RuntimeException("Brand not found"));
        brandRepository.delete(brand);
    }
}
