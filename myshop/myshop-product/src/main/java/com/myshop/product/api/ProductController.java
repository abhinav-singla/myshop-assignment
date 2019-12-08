package com.myshop.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.product.entity.ProductEntity;
import com.myshop.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/{id}")
	public ProductEntity get(@PathVariable Long id) {
		return productService.get(id);
	}
	
	@GetMapping("/products")
	public List<ProductEntity> get() {
		return productService.findAll();
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return "Product Deleted";
	}
	
	@PostMapping("/products")
	public ProductEntity createProduct(@RequestBody ProductEntity product) {
		return productService.create(product);
	}
	
	@PutMapping("/products/{id}")
	public ProductEntity updateProduct(@RequestBody ProductEntity product, @PathVariable Long id) {
		return productService.create(product);
	}
}
