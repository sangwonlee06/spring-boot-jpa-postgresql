package com.sangwon.springbootjpapostgresql.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @Schema(description = "Name of the category", example = "Smartphone")
    @NotEmpty(message = "Category name is required.")
    @Size(min = 2, message = "Category name must have at least 2 characters.")
    private String name;

    @Schema(description = "Description of the category", example = "A category for smartphones and mobile devices.")
    @NotEmpty(message = "Category description is required.")
    @Size(min = 10, message = "Category description must have at least 10 characters.")
    private String description;
}
