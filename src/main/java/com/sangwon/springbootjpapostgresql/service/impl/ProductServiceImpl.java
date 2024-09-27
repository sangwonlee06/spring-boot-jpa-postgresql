package com.sangwon.springbootjpapostgresql.service.impl;

import com.sangwon.springbootjpapostgresql.dto.request.ProductRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ProductResponseDTO;
import com.sangwon.springbootjpapostgresql.entity.Product;
import com.sangwon.springbootjpapostgresql.exception.ResourceNotFoundException;
import com.sangwon.springbootjpapostgresql.repository.ProductRepository;
import com.sangwon.springbootjpapostgresql.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return toResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> pagedProducts = productRepository.findAll(pageable);

        return pagedProducts.getContent()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

//        List<Product> products = productRepository.findAll();
//        return products.stream()
//                .map(this::toResponseDto)
//                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id",  id));
        return toResponseDto(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id",  id));

        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(productRequestDTO.getCategory());

        Product updatedProduct = productRepository.save(product);

        return toResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id",  id));
        productRepository.delete(product);
    }

    // Map Product entity to ProductResponseDTO
    private ProductResponseDTO toResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Map ProductRequestDTO to Product entity
    private Product toEntity(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }
}
