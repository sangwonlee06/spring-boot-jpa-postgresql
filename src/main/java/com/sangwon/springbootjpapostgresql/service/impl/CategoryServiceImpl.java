package com.sangwon.springbootjpapostgresql.service.impl;

import com.sangwon.springbootjpapostgresql.dto.request.CategoryRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.request.ProductRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.CategoryResponseDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ProductResponseDTO;
import com.sangwon.springbootjpapostgresql.entity.Category;
import com.sangwon.springbootjpapostgresql.entity.Product;
import com.sangwon.springbootjpapostgresql.exception.ResourceNotFoundException;
import com.sangwon.springbootjpapostgresql.repository.CategoryRepository;
import com.sangwon.springbootjpapostgresql.service.CategoryService;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = toEntity(categoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = toResponseDto(savedCategory);
        return categoryResponseDTO;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDtoList = categoryList.stream().map(category -> toResponseDto(category)).collect(Collectors.toList());
        return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return toResponseDto(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        Category updatedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        updatedCategory.setName(categoryRequestDTO.getName());
        updatedCategory.setDescription(categoryRequestDTO.getDescription());
        Category savedCategory = categoryRepository.save(updatedCategory);
        return toResponseDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);
    }

    // Map Category entity to CategoryResponseDTO
    private CategoryResponseDTO toResponseDto(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    // Map CategoryRequestDTO to Category entity
    private Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        return modelMapper.map(categoryRequestDTO, Category.class);
    }
}
