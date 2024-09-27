package com.sangwon.springbootjpapostgresql.service;

import com.sangwon.springbootjpapostgresql.dto.request.ProductRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortOrder);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);
}
