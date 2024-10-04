package com.sangwon.springbootjpapostgresql.service;

import com.sangwon.springbootjpapostgresql.dto.request.CategoryRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategoryById(Long categoryId);
    CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long categoryId);

}
