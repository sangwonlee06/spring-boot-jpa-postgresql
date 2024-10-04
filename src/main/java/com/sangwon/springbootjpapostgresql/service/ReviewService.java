package com.sangwon.springbootjpapostgresql.service;

import com.sangwon.springbootjpapostgresql.dto.request.ReviewRequestDTO;
import com.sangwon.springbootjpapostgresql.dto.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(Long productId, ReviewRequestDTO reviewRequestDTO);
    List<ReviewResponseDTO> getReviewsByProductId(Long productId);
    ReviewResponseDTO getReviewById(Long productId, Long reviewId);
    ReviewResponseDTO updateReview(Long productId, Long reviewId, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long productId, Long reviewId);
}
