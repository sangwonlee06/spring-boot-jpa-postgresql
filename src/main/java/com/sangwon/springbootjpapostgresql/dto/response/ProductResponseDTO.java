package com.sangwon.springbootjpapostgresql.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "Name of the product", example = "iPhone")
    private String name;

    @Schema(description = "Description of the product", example = "Latest Apple iPhone with cutting-edge features.")
    private String description;

    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;

    @Schema(description = "Category of the product", example = "Smartphone")
    private String category;

    @Schema(description = "Current page number in pagination", example = "0")
    private int pageNumber;

    @Schema(description = "Size of the page (number of items per page)", example = "10")
    private int pageSize;

    @Schema(description = "Total number of elements (products) in the result", example = "100")
    private int totalElements;

    @Schema(description = "Total number of pages in the result", example = "10")
    private int totalPages;

    @Schema(description = "Indicates if the current page is the last page", example = "true")
    private boolean last;
}

