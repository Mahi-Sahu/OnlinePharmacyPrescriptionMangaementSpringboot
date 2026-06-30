package com.cg.service.impl;

import com.cg.dto.request.CategoryRequestDto;
import com.cg.dto.response.CategoryResponseDto;
import com.cg.entity.Category;
import com.cg.repository.CategoryRepository;
import com.cg.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            return null;
        }
        return categories.stream().map(c->modelMapper.map(c,CategoryResponseDto.class)).toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getActiveCategories() {
        List<Category> categories=categoryRepository.findCategoryByIsActive(1);
        if(categories.isEmpty()) {
            return null;
        }
        return categories.stream().map(c->modelMapper.map(c,CategoryResponseDto.class)).toList();
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category=new Category();
        category.setIsActive(0);
        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setDescription(categoryRequestDto.getDescription());
        category.setCreatedAt(LocalDateTime.now());
        category.setImageUrl(categoryRequestDto.getImageUrl());
        categoryRepository.saveAndFlush(category);
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        if(category.getCategoryName()!=null){
            category.setCategoryName(categoryRequestDto.getCategoryName());
        }
        if(category.getDescription()!=null){
            category.setDescription(categoryRequestDto.getDescription());
        }
        if(category.getImageUrl()!=null){
            category.setImageUrl(categoryRequestDto.getImageUrl());
        }
        if(category.getIsActive()!=null){
            category.setIsActive(categoryRequestDto.getIsActive());
        }
        categoryRepository.saveAndFlush(category);
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}
