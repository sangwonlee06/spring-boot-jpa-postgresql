package com.sangwon.springbootjpapostgresql.repository;

import com.sangwon.springbootjpapostgresql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
