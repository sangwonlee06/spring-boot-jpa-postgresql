package com.sangwon.springbootjpapostgresql.controller;

import com.sangwon.springbootjpapostgresql.dto.request.ReviewRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ReviewResponseDTO;
import com.sangwon.springbootjpapostgresql.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/products/{productId}/reviews")
    @Operation(
            summary = "Create a new review for a product",
            description = "Create a new review by providing review details in the request body and associating it with a product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Review successfully created"
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ReviewResponseDTO> createReview(
            @PathVariable(value = "productId") Long productId,
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        return ResponseEntity.ok(reviewService.createReview(productId, reviewRequestDTO));
    }

    @GetMapping("/products/{productId}/reviews")
    @Operation(
            summary = "Retrieve all reviews for a product",
            description = "Get a list of all reviews associated with a product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of reviews"
            ),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public List<ReviewResponseDTO> getAllReviews(@PathVariable(value = "productId") Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/products/{productId}/reviews/{reviewId}")
    @Operation(
            summary = "Retrieve a review by ID",
            description = "Fetch a single review by its ID and associated product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved review details"
            ),
            @ApiResponse(responseCode = "404", description = "Review or Product not found")
    })
    public ResponseEntity<ReviewResponseDTO> getReviewById(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "reviewId") Long reviewId
    ) {
        return ResponseEntity.ok(reviewService.getReviewById(productId, reviewId));
    }

    @PutMapping("/products/{productId}/reviews/{reviewId}")
    @Operation(
            summary = "Update an existing review",
            description = "Update the details of an existing review by its ID and associated product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated the review"
            ),
            @ApiResponse(responseCode = "404", description = "Review or Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "reviewId") Long reviewId,
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO
    ) {
        return ResponseEntity.ok(reviewService.updateReview(productId, reviewId, reviewRequestDTO));
    }

    @DeleteMapping("/products/{productId}/reviews/{reviewId}")
    @Operation(
            summary = "Delete a review",
            description = "Delete a review by its ID and associated product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Review successfully deleted"
            ),
            @ApiResponse(responseCode = "404", description = "Review or Product not found")
    })
    public ResponseEntity<Void> deleteReview(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "reviewId") Long reviewId
    ) {
        reviewService.deleteReview(productId, reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
