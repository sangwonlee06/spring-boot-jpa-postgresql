package com.sangwon.springbootjpapostgresql.service.impl;

import com.sangwon.springbootjpapostgresql.dto.request.ReviewRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ReviewResponseDTO;
import com.sangwon.springbootjpapostgresql.entity.Product;
import com.sangwon.springbootjpapostgresql.entity.Review;
import com.sangwon.springbootjpapostgresql.exception.ResourceNotFoundException;
import com.sangwon.springbootjpapostgresql.repository.ProductRepository;
import com.sangwon.springbootjpapostgresql.repository.ReviewRepository;
import com.sangwon.springbootjpapostgresql.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            ProductRepository productRepository,
            ModelMapper modelMapper
    ) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewResponseDTO createReview(Long productId, ReviewRequestDTO reviewRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Review review = toEntity(reviewRequestDTO);
        review.setProduct(product);
        Review savedReview = reviewRepository.save(review);

        return toResponseDto(savedReview);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        List<Review> reviews = reviewRepository.getReviewsByProductId(productId);
        return reviews.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO getReviewById(Long productId, Long reviewId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        return toResponseDto(review);
    }

    @Override
    public ReviewResponseDTO updateReview(Long productId, Long reviewId, ReviewRequestDTO reviewRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        review.setTitle(reviewRequestDTO.getTitle());
        review.setContent(reviewRequestDTO.getContent());
        review.setAuthor(reviewRequestDTO.getAuthor());
        review.setRating(reviewRequestDTO.getRating());

        Review updatedReview = reviewRepository.save(review);
        return toResponseDto(updatedReview);

    }

    @Override
    public void deleteReview(Long productId, Long reviewId) {
        productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
        reviewRepository.delete(review);

    }

    // Map Review entity to ReviewResponseDTO
    private ReviewResponseDTO toResponseDto(Review review) {
        return modelMapper.map(review, ReviewResponseDTO.class);
    }

    // Map ReviewRequestDTO to Review entity
    private Review toEntity(ReviewRequestDTO reviewRequestDTO) {
        Review review = modelMapper.map(reviewRequestDTO, Review.class);
        review.setId(null);
        return review;
    }
}
