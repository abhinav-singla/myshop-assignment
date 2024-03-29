package com.myshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
