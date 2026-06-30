package com.cg.service;

import com.cg.dto.request.CategoryRequestDto;
import com.cg.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
    List<CategoryResponseDto> getActiveCategories();
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto updateCategory(Long categoryId,CategoryRequestDto categoryRequestDto);
    void deleteCategoryById(Long categoryId);
}
