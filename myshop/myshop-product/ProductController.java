package com.myshop.product.api;

import java.util.ArrayList;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/{id}")
	@ApiOperation(value = "Find the product details by providing product id",
		notes = "Provide an id of a product to find the product details",
		response = ProductEntity.class)
	public ProductEntity getProductById(
			@ApiParam(value = "Id of the product", required = true)
			@PathVariable long id) {
		return productService.get(id);
	}
	
	@GetMapping("/products/")
	@ApiOperation(value = "Find the product details for all the products",
				notes = "Get the product details for all the products",
				response = ArrayList.class)
	public List<ProductEntity> getAllProducts() {
		return productService.findAll();
	}
	
	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "Delete the product by providing product id",
				notes = "Provide an id of a product to find the product",
				response = String.class)
	public String deleteProduct(
			@ApiParam(value = "Id of the product", required = true) 
			@PathVariable long id) {
		productService.delete(id);
		return "Product Deleted";
	}
	
	@PostMapping("/products/")
	@ApiOperation(value = "Create a product by providing product details",
				notes = "Provide the product details to create a product",
				response = String.class)
	public ProductEntity createProduct(
			@ApiParam(value = "The product details which need to be created", required = true) 
			@RequestBody ProductEntity product) {
		return productService.create(product);
	}
	
	@PutMapping("/products/{id}")
	@ApiOperation(value = "Update a product by providing product details",
				notes = "Provide an id of a product and products details to update it",
				response = String.class)
	public ProductEntity updateProduct(
			@ApiParam(value = "The product details which need to be updated", required = true) 
			@RequestBody ProductEntity product,
			@ApiParam(value = "Id of the product", required = true) 
			@PathVariable long id) {
		return productService.create(product);
	}
}
