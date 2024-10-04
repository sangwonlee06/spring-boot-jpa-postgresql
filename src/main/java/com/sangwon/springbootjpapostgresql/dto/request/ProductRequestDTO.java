package com.sangwon.springbootjpapostgresql.dto.request;

import com.sangwon.springbootjpapostgresql.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @Schema(description = "Name of the product", example = "iPhone")
    @NotEmpty(message = "Product name is required.")
    @Size(min = 2, message = "Product name must have at least 2 characters.")
    private String name;

    @Schema(description = "Description of the product", example = "Latest Apple iPhone with cutting-edge features.")
    @NotEmpty(message = "Product description is required.")
    @Size(min = 10, message = "Product description must have at least 10 characters.")
    private String description;

    @Schema(description = "Price of the product", example = "999.99")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    private BigDecimal price;

    @Schema(description = "Category of the product", example = "Smartphone")
    @NotEmpty(message = "Product category is required.")
    private Long categoryId;
}
