package com.sangwon.springbootjpapostgresql.repository;

import com.sangwon.springbootjpapostgresql.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByProductId(Long productId);
}
