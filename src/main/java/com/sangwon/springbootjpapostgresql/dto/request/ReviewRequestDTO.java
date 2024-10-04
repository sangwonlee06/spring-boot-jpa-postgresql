package com.sangwon.springbootjpapostgresql.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @Schema(description = "Title of the review", example = "Great product!")
    @NotEmpty(message = "Review title is required.")
    @Size(min = 2, max = 100, message = "Review title must have between 2 and 100 characters.")
    private String title;

    @Schema(description = "Content of the review", example = "This product exceeded my expectations in every way.")
    @NotEmpty(message = "Review content is required.")
    @Size(min = 10, max = 1000, message = "Review content must have between 10 and 1000 characters.")
    private String content;

    @Schema(description = "Author of the review", example = "John Doe")
    @NotEmpty(message = "Author name is required.")
    @Size(min = 2, max = 50, message = "Author name must have between 2 and 50 characters.")
    private String author;

    @Schema(description = "Rating of the product", example = "4")
    @NotNull(message = "Rating is required.")
    @Min(value = 0, message = "Rating must be at least 0.")
    @Max(value = 5, message = "Rating must be at most 5.")
    private Integer rating;

//    @Schema(description = "Product ID associated with the review", example = "1")
//    @NotNull(message = "Product ID is required.")
//    private Long productId;
}
