package com.cg.controller;

import com.cg.dto.request.BrandRequestDto;
import com.cg.dto.response.BrandResponseDto;
import com.cg.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    private final BrandService brandService;

    BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return new  ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{brandName}")
    public ResponseEntity<BrandResponseDto> getBrandByName(@PathVariable String brandName) {
        return new  ResponseEntity<>(brandService.getBrandByName(brandName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody BrandRequestDto brandRequestDto) {
        return new  ResponseEntity<>(brandService.createBrand(brandRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> updateBrand(@PathVariable Long brandId, @RequestBody BrandRequestDto brandRequestDto) {
        return new  ResponseEntity<>(brandService.updateBrand(brandId, brandRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
