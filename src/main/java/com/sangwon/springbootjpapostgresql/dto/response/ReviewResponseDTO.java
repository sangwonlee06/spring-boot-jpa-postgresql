package com.sangwon.springbootjpapostgresql.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    @Schema(description = "Unique identifier of the review", example = "1")
    private Long id;

    @Schema(description = "Title of the review", example = "Great product!")
    private String title;

    @Schema(description = "Content of the review", example = "This product exceeded my expectations in every way.")
    private String content;

    @Schema(description = "Author of the review", example = "John Doe")
    private String author;

    @Schema(description = "Rating of the product", example = "4")
    private Integer rating;

    @Schema(description = "Product ID associated with the review", example = "1")
    private Long productId;
}
