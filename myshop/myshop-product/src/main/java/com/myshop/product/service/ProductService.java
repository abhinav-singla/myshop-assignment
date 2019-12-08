package com.myshop.product.service;

import java.util.List;

import com.myshop.product.entity.ProductEntity;

public interface ProductService {

	ProductEntity create(ProductEntity product);
	
	ProductEntity update(ProductEntity product);
	
	ProductEntity get(Long id);
	
	List<ProductEntity> findAll();
	
	void delete(Long id);
	
}
