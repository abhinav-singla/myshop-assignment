package com.myshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.product.entity.ProductEntity;
import com.myshop.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductEntity create(ProductEntity product) {
		return productRepository.save(product);
	}

	@Override
	public ProductEntity update(ProductEntity product) {
		if (null == productRepository.getOne(product.getId())) {
			return null;
		}
		return productRepository.save(product);
	}

	@Override
	public ProductEntity get(Long id) {
		return productRepository.getOne(id);
	}

	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
}
