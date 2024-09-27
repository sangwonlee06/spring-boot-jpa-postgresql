package com.sangwon.springbootjpapostgresql.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotEmpty(message = "Product name is required.")
    @Size(min = 2, message = "Product name must have at least 2 characters.")
    private String name;

    @NotEmpty(message = "Product description is required.")
    @Size(min = 10, message = "Product description must have at least 10 characters.")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotEmpty(message = "Product category is required.")
    private String category;
}
